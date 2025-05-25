package com.soptie.server.domain.customroutine;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.customroutine.dto.CustomRoutineRequestDto;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomRoutineCommandService {

	private final MemberRoutineAdapter memberRoutineAdapter;

	public MemberRoutine create(long memberId, @NotNull CustomRoutineRequestDto requestDto) {
		MemberRoutine memberRoutine = MemberRoutine.builder()
			.memberId(memberId)
			.content(requestDto.content())
			.themeId(requestDto.themeId())
			.alarmTime(requestDto.alarmTime())
			.build();

		return memberRoutineAdapter.save(memberRoutine);
	}

	public MemberRoutine update(long memberId, long customRoutineId, @NotNull CustomRoutineRequestDto requestDto) {
		MemberRoutine memberRoutine = memberRoutineAdapter.findById(customRoutineId);

		if (memberRoutine.getMemberId() != memberId) {
			return memberRoutine;
		}

		memberRoutine.setContent(requestDto.content());
		memberRoutine.setThemeId(requestDto.themeId());
		memberRoutine.setAlarmTime(requestDto.alarmTime());

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
