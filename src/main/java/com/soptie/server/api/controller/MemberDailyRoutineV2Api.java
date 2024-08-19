package com.soptie.server.api.controller;

import static com.soptie.server.api.controller.dto.response.SuccessResponse.success;
import static com.soptie.server.common.message.MemberRoutineSuccessMassage.SUCCESS_CREATE_ROUTINE;
import static com.soptie.server.common.message.MemberRoutineSuccessMassage.SUCCESS_GET_ROUTINE;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.request.memberroutine.MemberDailyRoutinesCreateRequest;
import com.soptie.server.api.controller.docs.MemberDailyRoutineV2ApiDocs;
import com.soptie.server.api.controller.dto.response.memberroutine.MemberDailyRoutineListAcquireResponseV2;
import com.soptie.server.api.controller.dto.response.memberroutine.MemberDailyRoutinesCreateResponse;
import com.soptie.server.domain.memberroutine.MemberRoutineCreateService;
import com.soptie.server.domain.memberroutine.MemberRoutineReadService;
import com.soptie.server.domain.memberroutine.MemberDailyRoutineListAcquireServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/daily/member")
public class MemberDailyRoutineV2Api implements MemberDailyRoutineV2ApiDocs {

	private final MemberRoutineReadService memberRoutineReadService;
	private final MemberRoutineCreateService memberRoutineCreateService;

	@GetMapping
	public ResponseEntity<SuccessResponse<MemberDailyRoutineListAcquireResponseV2>> acquireAll(
		Principal principal
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutineListAcquireResponseV2
			.of(memberRoutineReadService.acquireAll(MemberDailyRoutineListAcquireServiceRequest.of(memberId)));
		return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SuccessResponse<MemberDailyRoutinesCreateResponse> createMemberDailyRoutines(
		Principal principal,
		MemberDailyRoutinesCreateRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutinesCreateResponse
			.of(memberRoutineCreateService.createDailyRoutines(memberId, request));
		return SuccessResponse.success(SUCCESS_CREATE_ROUTINE.getMessage(), response);
	}
}
