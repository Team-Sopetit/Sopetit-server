package com.soptie.server.api.controller.customroutine;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.customroutine.docs.CustomRoutineApiDocs;
import com.soptie.server.api.controller.customroutine.dto.CustomRoutineRequestDto;
import com.soptie.server.api.controller.customroutine.dto.CustomRoutineResponseDto;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.domain.customroutine.CustomRoutineCommandService;
import com.soptie.server.domain.memberroutine.MemberRoutine;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/custom")
public class CustomRoutineApi implements CustomRoutineApiDocs {

	private final CustomRoutineCommandService commandService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public SuccessResponse<CustomRoutineResponseDto> create(
		Principal principal,
		@RequestBody CustomRoutineRequestDto requestDto
	) {
		long memberId = Long.parseLong(principal.getName());
		MemberRoutine response = commandService.create(memberId, requestDto);
		return SuccessResponse.of(convert(response));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{customRoutineId}")
	public SuccessResponse<CustomRoutineResponseDto> update(
		Principal principal,
		@PathVariable long customRoutineId,
		@RequestBody CustomRoutineRequestDto requestDto
	) {
		long memberId = Long.parseLong(principal.getName());
		MemberRoutine response = commandService.update(memberId, customRoutineId, requestDto);
		return SuccessResponse.of(convert(response));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{customRoutineId}")
	public SuccessResponse<?> delete(Principal principal, @PathVariable long customRoutineId) {
		long memberId = Long.parseLong(principal.getName());
		commandService.delete(memberId, customRoutineId);
		return SuccessResponse.of(null);
	}

	private CustomRoutineResponseDto convert(MemberRoutine memberRoutine) {
		return CustomRoutineResponseDto.builder()
			.id(memberRoutine.getId())
			.content(memberRoutine.getContent())
			.themeId(memberRoutine.getThemeId())
			.alarmTime(memberRoutine.getAlarmTime())
			.build();
	}
}
