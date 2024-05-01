package com.soptie.server.memberRoutine.controller.v1;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.memberRoutine.message.SuccessMessage.*;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.memberRoutine.controller.v1.dto.response.MemberDailyRoutineAchieveResponse;
import com.soptie.server.memberRoutine.controller.v1.dto.response.MemberDailyRoutineCreateResponse;
import com.soptie.server.memberRoutine.controller.v1.dto.response.MemberDailyRoutineListGetResponse;
import com.soptie.server.memberRoutine.service.MemberRoutineService;
import com.soptie.server.memberRoutine.service.daily.MemberDailyRoutineService;
import com.soptie.server.memberRoutine.controller.v1.dto.request.MemberDailyRoutineCreateRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineListGetServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily/member")
public class MemberDailyRoutineController implements MemberDailyRoutineApi {

	private final MemberDailyRoutineService memberDailyRoutineService;
	private final MemberRoutineService memberRoutineService;

	@PostMapping
	public ResponseEntity<SuccessResponse<MemberDailyRoutineCreateResponse>> createMemberDailyRoutine(
			Principal principal,
			@RequestBody MemberDailyRoutineCreateRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutineCreateResponse.of(memberDailyRoutineService
				.createMemberDailyRoutine(MemberDailyRoutineCreateServiceRequest.of(memberId, request)));
		return ResponseEntity.created(getURI()).body(of(SUCCESS_CREATE_ROUTINE.getMessage(), response));
	}

	@DeleteMapping
	public ResponseEntity<BaseResponse> deleteMemberDailyRoutines(
			Principal principal,
			@RequestParam List<Long> routines
	) {
		val memberId = Long.parseLong(principal.getName());
		memberDailyRoutineService
				.deleteMemberDailyRoutines(MemberDailyRoutineDeleteServiceRequest.of(memberId, routines));
		return ResponseEntity.ok(of(SUCCESS_DELETE_ROUTINE.getMessage()));
	}

	@PatchMapping("/routine/{routineId}")
	public ResponseEntity<SuccessResponse<MemberDailyRoutineAchieveResponse>> achieveMemberDailyRoutine(
			Principal principal,
			@PathVariable long routineId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutineAchieveResponse.of(memberDailyRoutineService
				.achieveMemberDailyRoutine(MemberDailyRoutineAchieveServiceRequest.of(memberId, routineId)));
		return ResponseEntity.ok(of(SUCCESS_ACHIEVE_ROUTINE.getMessage(), response));
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<MemberDailyRoutineListGetResponse>> getMemberDailyRoutines(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutineListGetResponse.of(memberDailyRoutineService
				.getMemberDailyRoutines(MemberDailyRoutineListGetServiceRequest.of(memberId)));
		return ResponseEntity.ok(of(SUCCESS_GET_ROUTINE.getMessage(), response));
	}

	private URI getURI() {
		return ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/")
				.buildAndExpand()
				.toUri();
	}
}
