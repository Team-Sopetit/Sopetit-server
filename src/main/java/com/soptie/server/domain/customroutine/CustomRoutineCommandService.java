package com.soptie.server.domain.customroutine;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.customroutine.dto.CustomRoutineRequest;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomRoutineCommandService {

	private final MemberRoutineAdapter memberRoutineAdapter;

	public MemberRoutine create(long memberId, @NotNull CustomRoutineRequest request) {
		MemberRoutine memberRoutine = MemberRoutine.builder()
			.memberId(memberId)
			.content(request.content())
			.themeId(request.themeId())
			.alarmTime(request.alarmTime())
			.build();

		return memberRoutineAdapter.save(memberRoutine);
	}

	public MemberRoutine update(long memberId, long customRoutineId, @NotNull CustomRoutineRequest request) {
		MemberRoutine memberRoutine = memberRoutineAdapter.findById(customRoutineId);

		if (memberRoutine.getMemberId() != memberId) {
			return memberRoutine;
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

		memberRoutineAdapter.deleteForce(memberRoutine);
	}
}
