package com.soptie.server.domain.customroutine;

import java.time.LocalTime;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.customroutine.dto.CustomRoutineRequest;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.memberroutine.RoutineAlarm;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAlarmAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineHistoryAdapter;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomRoutineCommandService {

	private final MemberRoutineAdapter memberRoutineAdapter;
	private final RoutineAlarmAdapter routineAlarmAdapter;
	private final RoutineHistoryAdapter routineHistoryAdapter;

	public MemberRoutine create(long memberId, @NotNull CustomRoutineRequest request) {
		MemberRoutine memberRoutine = memberRoutineAdapter.save(MemberRoutine.builder()
			.memberId(memberId)
			.content(request.content())
			.themeId(request.themeId())
			.alarmTime(request.alarmTime())
			.build()
		);
		if (Objects.nonNull(request.alarmTime())) {
			saveRoutineAlarm(memberRoutine);
		}
		return memberRoutine;
	}

	public MemberRoutine update(long memberId, long customRoutineId, @NotNull CustomRoutineRequest request) {
		MemberRoutine memberRoutine = memberRoutineAdapter.findById(customRoutineId);

		if (memberRoutine.getMemberId() != memberId) {
			return memberRoutine;
		}

		if (Objects.nonNull(memberRoutine.getAlarmTime()) && Objects.isNull(request.alarmTime())) {
			// 알람 제거
			deleteRoutineAlarm(memberRoutine);
		} else if (Objects.nonNull(memberRoutine.getAlarmTime())) {
			// 알람 변동
			updateRoutineAlarm(memberRoutine, request.alarmTime());
		} else if (Objects.nonNull(request.alarmTime())) {
			// 알람 생성
			saveRoutineAlarm(memberRoutine);
		}

		memberRoutine.setContent(request.content());
		memberRoutine.setThemeId(request.themeId());
		memberRoutine.setAlarmTime(request.alarmTime());

		return memberRoutineAdapter.updateAll(memberRoutine);
	}

	public void delete(long memberId, long customRoutineId) {
		MemberRoutine memberRoutine = memberRoutineAdapter.findById(customRoutineId);

		if (memberRoutine.getMemberId() != memberId) {
			return;
		}

		if (Objects.nonNull(memberRoutine.getAlarmTime())) {
			deleteRoutineAlarm(memberRoutine);
		}

		memberRoutineAdapter.deleteForce(memberRoutine);
		routineHistoryAdapter.deleteByRoutineId(memberRoutine.getId());
	}

	private void saveRoutineAlarm(MemberRoutine memberRoutine) {
		RoutineAlarm routineAlarm = RoutineAlarm.builder()
			.memberId(memberRoutine.getMemberId())
			.memberRoutineId(memberRoutine.getId())
			.alarmTime(memberRoutine.getAlarmTime())
			.build();
		routineAlarmAdapter.save(routineAlarm);
	}

	private void updateRoutineAlarm(MemberRoutine memberRoutine, LocalTime alarmTime) {
		RoutineAlarm routineAlarm = routineAlarmAdapter.findByMemberRoutineAlarmId(memberRoutine.getId());
		routineAlarm.setAlarmTime(alarmTime);
		routineAlarmAdapter.update(routineAlarm);
	}

	private void deleteRoutineAlarm(MemberRoutine memberRoutine) {
		routineAlarmAdapter.deleteByMemberRoutineId(memberRoutine.getId());
	}
}
