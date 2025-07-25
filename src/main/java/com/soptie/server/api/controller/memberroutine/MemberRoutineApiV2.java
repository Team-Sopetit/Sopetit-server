package com.soptie.server.api.controller.memberroutine;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.common.constants.MessageConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.memberroutine.docs.MemberRoutineApiV2Docs;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesRequest;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesResponse;
import com.soptie.server.api.controller.memberroutine.dto.GetMemberRoutinesResponse;
import com.soptie.server.api.controller.memberroutine.dto.UpdateMemberRoutineRequest;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.memberroutine.MemberRoutineReadService;
import com.soptie.server.domain.memberroutine.MemberRoutineService;
import com.soptie.server.domain.theme.Theme;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/daily/member")
public class MemberRoutineApiV2 implements MemberRoutineApiV2Docs {

	private final MemberRoutineService memberRoutineService;
	private final MemberRoutineReadService readService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public SuccessResponse<CreateMemberRoutinesResponse> createMemberRoutines(
		Principal principal,
		CreateMemberRoutinesRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberRoutineService.createRoutines(memberId, request);
		return SuccessResponse.success(MessageConstants.CREATE_ROUTINE.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public SuccessResponse<GetMemberRoutinesResponse> getMemberRoutines(Principal principal) {
		long memberId = Long.parseLong(principal.getName());
		Map<Theme, List<MemberRoutine>> response = readService.getAllWithTheme(memberId);
		return SuccessResponse.from(GetMemberRoutinesResponse.from(response));
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/member-routines/{memberRoutineId}")
	public SuccessResponse<?> updateMemberRoutine(
		Principal principal,
		@PathVariable long memberRoutineId,
		@RequestBody UpdateMemberRoutineRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		memberRoutineService.updateMemberRoutine(memberId, memberRoutineId, request);
		return SuccessResponse.from();
	}
}
