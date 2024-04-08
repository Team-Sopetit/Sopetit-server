package com.soptie.server.memberRoutine.controller.daily;

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
import com.soptie.server.memberRoutine.dto.AchievedMemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutinesResponse;
import com.soptie.server.memberRoutine.service.MemberDailyRoutineService;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily/member")
public class MemberDailyRoutineController implements MemberDailyRoutineApi {

	private final MemberDailyRoutineService memberDailyRoutineService;

	@PostMapping
	public ResponseEntity<SuccessResponse<MemberDailyRoutineResponse>> createMemberDailyRoutine(
			Principal principal,
			@RequestBody MemberDailyRoutineRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberDailyRoutineService.createMemberDailyRoutine(memberId, request);
		return ResponseEntity.created(getURI()).body(of(SUCCESS_CREATE_ROUTINE.getMessage(), response));
	}

	private URI getURI() {
		return ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/")
			.buildAndExpand()
			.toUri();
	}

	@DeleteMapping
	public ResponseEntity<BaseResponse> deleteMemberDailyRoutines(
			Principal principal,
			@RequestParam List<Long> routines
	) {
		val memberId = Long.parseLong(principal.getName());
		memberDailyRoutineService.deleteMemberDailyRoutines(memberId, routines);
		return ResponseEntity.ok(of(SUCCESS_DELETE_ROUTINE.getMessage()));
	}

	@PatchMapping("/routine/{routineId}")
	public ResponseEntity<SuccessResponse<AchievedMemberDailyRoutineResponse>> achieveMemberDailyRoutine(
			Principal principal,
			@PathVariable Long routineId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberDailyRoutineService.achieveMemberDailyRoutine(memberId, routineId);
		return ResponseEntity.ok(of(SUCCESS_ACHIEVE_ROUTINE.getMessage(), response));
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<MemberDailyRoutinesResponse>> getMemberDailyRoutines(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberDailyRoutineService.getMemberDailyRoutines(memberId);
		return ResponseEntity.ok(of(SUCCESS_GET_ROUTINE.getMessage(), response));
	}
}