package com.soptie.server.domain.memberroutine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.memberroutine.dto.AchieveMemberRoutineResponse;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesRequest;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesResponse;
import com.soptie.server.api.controller.memberroutine.dto.UpdateMemberRoutineRequest;
import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.utils.TimeUtils;
import com.soptie.server.domain.member.Member;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAlarmAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineHistoryAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberRoutineService {
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberAdapter memberAdapter;
	private final RoutineAdapter routineAdapter;
	private final RoutineHistoryAdapter routineHistoryAdapter;
	private final RoutineAlarmAdapter routineAlarmAdapter;

	public CreateMemberRoutinesResponse createRoutines(
		long memberId,
		CreateMemberRoutinesRequest request
	) {
		val member = memberAdapter.findById(memberId);
		val routines = routineAdapter.findByIds(request.routineIds());
		val savedMemberRoutines = memberRoutineAdapter.saveAll(member, routines);
		return CreateMemberRoutinesResponse.of(savedMemberRoutines);
	}

	public void deleteMemberRoutines(long memberId, List<Long> memberRoutineIds) {
		val memberRoutines = memberRoutineAdapter.findByIds(memberRoutineIds).stream()
			.filter(memberRoutine -> memberRoutine.getMemberId() == memberId)
			.toList();
		memberRoutines.forEach(memberRoutine -> routineAlarmAdapter.deleteByMemberRoutineId(memberRoutine.getId()));
		memberRoutineAdapter.deleteAll(memberRoutines);
	}

	public AchieveMemberRoutineResponse achieveOrCancel(long memberId, long memberRoutineId) {
		Member member = memberAdapter.findById(memberId);
		MemberRoutine memberRoutine = memberRoutineAdapter.findById(memberRoutineId);
		validateMemberRoutine(memberRoutine, memberId);

		boolean achievedToday = LocalDate.now().equals(TimeUtils.toDateTime(memberRoutine.getLastAchievedAt()));
		if (memberRoutine.isAchieved() && achievedToday) {
			cancelAchievement(memberRoutine);
		} else {
			achieve(memberRoutine, member, achievedToday);
		}

		return AchieveMemberRoutineResponse.of(memberRoutine, !achievedToday);
	}

	private void achieve(MemberRoutine memberRoutine, Member member, boolean achievedToday) {
		memberRoutine.setAchieved(true);
		memberRoutine.setLastAchievedAt(LocalDateTime.now());
		memberRoutine.setAchievementCount(memberRoutine.getAchievementCount() + 1);
		memberRoutineAdapter.update(memberRoutine);

		if (!achievedToday) {
			member.getCottonInfo().addBasicCottonCount();
			memberAdapter.update(member);
			routineHistoryAdapter.save(memberRoutine);
		}
	}

	private void cancelAchievement(MemberRoutine memberRoutine) {
		memberRoutine.setAchieved(false);
		memberRoutine.setAchievementCount(memberRoutine.getAchievementCount() - 1);
		memberRoutineAdapter.update(memberRoutine);
	}

	public void deleteHistory(long historyId) {
		val history = routineHistoryAdapter.findById(historyId);
		val memberRoutine = memberRoutineAdapter.findById(history.getMemberRoutineId());

		if (LocalDate.now().equals(TimeUtils.toDateTime(history.getCreatedAt()))) {
			memberRoutine.setAchieved(false);
		}

		memberRoutine.setAchievementCount(memberRoutine.getAchievementCount() - 1);
		memberRoutineAdapter.update(memberRoutine);
		routineHistoryAdapter.deleteById(historyId);
	}

	public void updateMemberRoutine(long memberId, long memberRoutineId, UpdateMemberRoutineRequest request) {
		memberAdapter.findById(memberId);
		MemberRoutine memberRoutine = memberRoutineAdapter.findById(memberRoutineId);
		validateMemberRoutine(memberRoutine, memberId);
		memberRoutine.setAlarmTime(request.alarmTime());
		memberRoutineAdapter.update(memberRoutine);
	}

	private void validateMemberRoutine(MemberRoutine memberRoutine, long memberId) {
		if (memberRoutine.getMemberId() != memberId) {
			throw new SoftieException(
				ExceptionCode.NOT_AVAILABLE,
				"Member ID: " + memberId + ", MemberRoutine ID: " + memberRoutine.getId());
		}
	}
}
