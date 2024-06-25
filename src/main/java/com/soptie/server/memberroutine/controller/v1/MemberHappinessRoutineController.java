package com.soptie.server.memberroutine.controller.v1;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.memberroutine.message.MemberRoutineSuccessMassage.*;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.common.support.UriGenerator;
import com.soptie.server.memberroutine.controller.v1.docs.MemberHappinessRoutineControllerDocs;
import com.soptie.server.memberroutine.controller.v1.dto.request.MemberHappinessRoutineRequest;
import com.soptie.server.memberroutine.controller.v1.dto.response.MemberHappinessRoutineCreateResponse;
import com.soptie.server.memberroutine.controller.v1.dto.response.MemberHappinessRoutineGetResponse;
import com.soptie.server.memberroutine.service.MemberRoutineCreateService;
import com.soptie.server.memberroutine.service.MemberRoutineDeleteService;
import com.soptie.server.memberroutine.service.MemberRoutineReadService;
import com.soptie.server.memberroutine.service.MemberRoutineUpdateService;
import com.soptie.server.memberroutine.service.dto.request.MemberHappinessRoutineCreateServiceRequest;
import com.soptie.server.memberroutine.service.dto.request.MemberHappinessRoutineGetServiceRequest;
import com.soptie.server.memberroutine.service.dto.request.MemberRoutineAchieveServiceRequest;
import com.soptie.server.memberroutine.service.dto.request.MemberRoutineDeleteServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness/member")
public class MemberHappinessRoutineController implements MemberHappinessRoutineControllerDocs {

	private final MemberRoutineCreateService memberRoutineCreateService;
	private final MemberRoutineReadService memberRoutineReadService;
	private final MemberRoutineUpdateService memberRoutineUpdateService;
	private final MemberRoutineDeleteService memberRoutineDeleteService;

	@PostMapping
	public ResponseEntity<SuccessResponse<MemberHappinessRoutineCreateResponse>> createMemberHappinessRoutine(
		Principal principal, @RequestBody MemberHappinessRoutineRequest request) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberHappinessRoutineCreateResponse.of(memberRoutineCreateService.createHappinessRoutine(
			MemberHappinessRoutineCreateServiceRequest.of(memberId, request)));
		return ResponseEntity
			.created(UriGenerator.getUri("/api/v1/routines/happiness/member", response.routineId()))
			.body(success(SUCCESS_CREATE_ROUTINE.getMessage(), response));
	}

	@GetMapping
	public ResponseEntity<?> getMemberHappinessRoutine(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		return memberRoutineReadService.getHappinessRoutine(MemberHappinessRoutineGetServiceRequest.of(memberId))
			.map(response -> ResponseEntity.ok(SuccessResponse.success(
				SUCCESS_GET_ROUTINE.getMessage(),
				MemberHappinessRoutineGetResponse.of(response))))
			.orElseGet(() -> ResponseEntity.noContent().build());
	}

	@DeleteMapping("/routine/{routineId}")
	public ResponseEntity<BaseResponse> deleteMemberHappinessRoutine(
		Principal principal,
		@PathVariable Long routineId
	) {
		val memberId = Long.parseLong(principal.getName());
		memberRoutineDeleteService.deleteMemberRoutine(MemberRoutineDeleteServiceRequest.of(memberId, routineId));
		return ResponseEntity.ok(success(SUCCESS_DELETE_ROUTINE.getMessage()));
	}

	@PatchMapping("/routine/{routineId}")
	public ResponseEntity<BaseResponse> achieveMemberHappinessRoutine(
		Principal principal,
		@PathVariable Long routineId
	) {
		val memberId = Long.parseLong(principal.getName());
		memberRoutineUpdateService.achieveMemberRoutine(MemberRoutineAchieveServiceRequest.of(memberId, routineId));
		return ResponseEntity.ok(success(SUCCESS_ACHIEVE_ROUTINE.getMessage()));
	}
}
