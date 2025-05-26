package com.soptie.server.domain.memberroutine;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.memberroutine.dto.AchieveMemberRoutineResponse;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesRequest;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesResponse;
import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineHistoryAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRoutineService {
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberAdapter memberAdapter;
	private final RoutineAdapter routineAdapter;
	private final RoutineHistoryAdapter routineHistoryAdapter;

	@Transactional
	public CreateMemberRoutinesResponse createRoutines(
		long memberId,
		CreateMemberRoutinesRequest request
	) {
		val member = memberAdapter.findById(memberId);
		val routines = routineAdapter.findByIds(request.routineIds());
		val savedMemberRoutines = memberRoutineAdapter.saveAll(member, routines);
		return CreateMemberRoutinesResponse.of(savedMemberRoutines);
	}

	@Transactional
	public void deleteMemberRoutines(long memberId, List<Long> memberRoutineIds) {
		val memberRoutines = memberRoutineAdapter.findByIds(memberRoutineIds).stream()
			.filter(memberRoutine -> memberRoutine.getMemberId() == memberId)
			.toList();
		memberRoutineAdapter.deleteAll(memberRoutines);
	}

	@Transactional
	public AchieveMemberRoutineResponse achieveMemberRoutine(long memberId, long memberRoutineId) {
		val member = memberAdapter.findById(memberId);
		val memberRoutine = memberRoutineAdapter.findById(memberRoutineId);
		val isAchievedToday = memberRoutine.isAchievedToday();

		if (memberRoutine.getMemberId() != memberId) {
			throw new SoftieException(
				ExceptionCode.NOT_AVAILABLE,
				"Member ID: " + memberId + ", MemberRoutine ID: " + memberRoutineId);
		}

		if (!isAchievedToday) {
			member.getCottonInfo().addBasicCottonCount();
			memberAdapter.update(member);
		}

		memberRoutine.achieve();
		memberRoutineAdapter.update(memberRoutine);
		updateHistory(memberRoutine, isAchievedToday);

		return AchieveMemberRoutineResponse.of(memberRoutine, !isAchievedToday);
	}

	private void updateHistory(MemberRoutine memberRoutine, boolean isAchievedToday) {
		if (isAchievedToday) {
			routineHistoryAdapter.deleteByRoutineIdAndCreatedAt(memberRoutine.getId(), LocalDate.now());
		} else {
			routineHistoryAdapter.save(memberRoutine);
		}
	}

	@Transactional
	public void initAchievement() {
		memberRoutineAdapter.initAllAchievement();
	}

	@Transactional
	public void deleteHistory(long historyId) {
		val history = routineHistoryAdapter.findById(historyId);
		val memberRoutine = memberRoutineAdapter.findById(history.getMemberRoutineId());
		memberRoutine.cancel(history.getCreatedAt().toLocalDate());
		memberRoutineAdapter.update(memberRoutine);
		routineHistoryAdapter.deleteById(historyId);
	}
}
