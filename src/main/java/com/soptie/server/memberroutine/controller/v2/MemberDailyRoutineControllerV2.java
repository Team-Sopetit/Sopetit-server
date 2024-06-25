package com.soptie.server.memberroutine.controller.v2;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.memberroutine.message.MemberRoutineSuccessMassage.*;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.memberroutine.controller.v2.docs.MemberDailyRoutineControllerV2Docs;
import com.soptie.server.memberroutine.controller.v2.dto.response.MemberDailyRoutineListAcquireResponseV2;
import com.soptie.server.memberroutine.service.MemberRoutineReadService;
import com.soptie.server.memberroutine.service.dto.request.MemberDailyRoutineListAcquireServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/daily/member")
public class MemberDailyRoutineControllerV2 implements MemberDailyRoutineControllerV2Docs {

	private final MemberRoutineReadService memberRoutineReadService;

	@GetMapping
	public ResponseEntity<SuccessResponse<MemberDailyRoutineListAcquireResponseV2>> acquireAll(
		Principal principal
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberDailyRoutineListAcquireResponseV2
			.of(memberRoutineReadService.acquireAll(MemberDailyRoutineListAcquireServiceRequest.of(memberId)));
		return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
	}
}
