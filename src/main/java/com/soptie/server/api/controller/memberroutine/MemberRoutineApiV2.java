package com.soptie.server.api.controller.memberroutine;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.api.controller.memberroutine.docs.MemberRoutineApiV2Docs;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesRequest;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesResponse;
import com.soptie.server.api.controller.memberroutine.dto.GetMemberRoutinesResponse;
import com.soptie.server.domain.memberroutine.MemberRoutineService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/daily/member")
public class MemberRoutineApiV2 implements MemberRoutineApiV2Docs {
	private final MemberRoutineService memberRoutineService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public SuccessResponse<CreateMemberRoutinesResponse> createMemberRoutines(
		Principal principal,
		CreateMemberRoutinesRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberRoutineService.createRoutines(memberId, request);
		return SuccessResponse.success(SuccessMessage.CREATE_ROUTINE.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public SuccessResponse<GetMemberRoutinesResponse> getMemberRoutines(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberRoutineService.getMemberRoutines(memberId);
		return SuccessResponse.success(SuccessMessage.GET_ROUTINE.getMessage(), response);
	}
}
