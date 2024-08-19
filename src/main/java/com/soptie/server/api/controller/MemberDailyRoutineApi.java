package com.soptie.server.api.controller;

import static com.soptie.server.api.controller.dto.response.SuccessResponse.*;
import static com.soptie.server.common.message.MemberRoutineSuccessMassage.*;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.dto.response.BaseResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.common.support.UriGenerator;
import com.soptie.server.api.controller.docs.MemberDailyRoutineApiDocs;
import com.soptie.server.api.controller.dto.request.memberroutine.MemberDailyRoutineCreateRequest;
import com.soptie.server.api.controller.dto.response.memberroutine.MemberDailyRoutineAchieveResponse;
import com.soptie.server.api.controller.dto.response.memberroutine.MemberDailyRoutineCreateResponse;
import com.soptie.server.api.controller.dto.response.memberroutine.MemberDailyRoutineListAcquireResponse;
import com.soptie.server.domain.memberroutine.MemberRoutineCreateService;
import com.soptie.server.domain.memberroutine.MemberRoutineDeleteService;
import com.soptie.server.domain.memberroutine.MemberRoutineReadService;
import com.soptie.server.domain.memberroutine.MemberRoutineUpdateService;
import com.soptie.server.domain.memberroutine.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.domain.memberroutine.MemberDailyRoutineListAcquireServiceRequest;
import com.soptie.server.domain.memberroutine.MemberRoutineAchieveServiceRequest;
import com.soptie.server.domain.memberroutine.MemberRoutinesDeleteServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily/member")
public class MemberDailyRoutineApi implements MemberDailyRoutineApiDocs {

	private final MemberRoutineCreateService memberRoutineCreateService;
	private final MemberRoutineReadService memberRoutineReadService;
	private final MemberRoutineUpdateService memberRoutineUpdateService;
	private final MemberRoutineDeleteService memberRoutineDeleteService;

	@PostMapping
	public ResponseEntity<SuccessResponse<MemberDailyRoutineCreateResponse>> createMemberDailyRoutine(
		Principal principal,
		@RequestBody MemberDailyRoutineCreateRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutineCreateResponse.of(memberRoutineCreateService.createDailyRoutine(
			MemberDailyRoutineCreateServiceRequest.of(memberId, request)));
		return ResponseEntity
			.created(UriGenerator.getUri("/api/v1/routines/daily/member/", response.routineId()))
			.body(success(SUCCESS_CREATE_ROUTINE.getMessage(), response));
	}

	@DeleteMapping
	public ResponseEntity<BaseResponse> deleteMemberDailyRoutines(
		Principal principal,
		@RequestParam List<Long> routines
	) {
		val memberId = Long.parseLong(principal.getName());
		memberRoutineDeleteService.deleteMemberRoutines(MemberRoutinesDeleteServiceRequest.of(memberId, routines));
		return ResponseEntity.ok(success(SUCCESS_DELETE_ROUTINE.getMessage()));
	}

	@PatchMapping("/routine/{routineId}")
	public ResponseEntity<SuccessResponse<MemberDailyRoutineAchieveResponse>> achieveMemberDailyRoutine(
		Principal principal,
		@PathVariable long routineId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutineAchieveResponse
			.of(memberRoutineUpdateService.updateAchievementStatus(
				MemberRoutineAchieveServiceRequest.of(memberId, routineId)));
		return ResponseEntity.ok(success(SUCCESS_ACHIEVE_ROUTINE.getMessage(), response));
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<MemberDailyRoutineListAcquireResponse>> getMemberDailyRoutines(
		Principal principal
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutineListAcquireResponse
			.of(memberRoutineReadService.getDailyRoutines(MemberDailyRoutineListAcquireServiceRequest.of(memberId)));
		return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
	}
}
