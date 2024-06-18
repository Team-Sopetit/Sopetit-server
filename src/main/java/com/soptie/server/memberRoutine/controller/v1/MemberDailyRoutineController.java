package com.soptie.server.memberRoutine.controller.v1;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.memberRoutine.message.SuccessMessage.*;

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

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.common.support.UriGenerator;
import com.soptie.server.memberRoutine.controller.v1.api.MemberDailyRoutineApi;
import com.soptie.server.memberRoutine.controller.v1.dto.response.MemberDailyRoutineAchieveResponse;
import com.soptie.server.memberRoutine.controller.v1.dto.response.MemberDailyRoutineCreateResponse;
import com.soptie.server.memberRoutine.controller.v1.dto.response.MemberDailyRoutineListGetResponse;
import com.soptie.server.memberRoutine.service.MemberRoutineCreateService;
import com.soptie.server.memberRoutine.controller.v1.dto.request.MemberDailyRoutineCreateRequest;
import com.soptie.server.memberRoutine.service.MemberRoutineDeleteService;
import com.soptie.server.memberRoutine.service.MemberRoutineReadService;
import com.soptie.server.memberRoutine.service.MemberRoutineUpdateService;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutinesDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineListGetServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily/member")
public class MemberDailyRoutineController implements MemberDailyRoutineApi {

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
				.created(UriGenerator.getURI("/api/v1/routines/daily/member/", response.routineId()))
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
				.of(memberRoutineUpdateService.achieveMemberRoutine(
						MemberRoutineAchieveServiceRequest.of(memberId, routineId)));
		return ResponseEntity.ok(success(SUCCESS_ACHIEVE_ROUTINE.getMessage(), response));
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<MemberDailyRoutineListGetResponse>> getMemberDailyRoutines(
			Principal principal
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutineListGetResponse
				.of(memberRoutineReadService.getDailyRoutines(MemberDailyRoutineListGetServiceRequest.of(memberId)));
		return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
	}
}
