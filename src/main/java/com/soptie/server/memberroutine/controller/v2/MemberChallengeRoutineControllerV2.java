package com.soptie.server.memberroutine.controller.v2;

import static com.soptie.server.memberroutine.message.MemberRoutineSuccessMassage.*;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.memberroutine.controller.v2.docs.MemberChallengeRoutineControllerV2Docs;
import com.soptie.server.memberroutine.controller.v2.dto.response.MemberChallengeRoutineAcquireResponseV2;
import com.soptie.server.memberroutine.service.MemberRoutineReadService;
import com.soptie.server.memberroutine.service.dto.request.MemberChallengeRoutineAcquireServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/challenge/member")
public class MemberChallengeRoutineControllerV2 implements MemberChallengeRoutineControllerV2Docs {

	private final MemberRoutineReadService memberRoutineReadService;

	@GetMapping
	public ResponseEntity<?> acquire(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		return memberRoutineReadService.acquire(MemberChallengeRoutineAcquireServiceRequest.of(memberId))
			.map(response -> ResponseEntity.ok(SuccessResponse.success(
				SUCCESS_GET_ROUTINE.getMessage(),
				MemberChallengeRoutineAcquireResponseV2.of(response))))
			.orElseGet(() -> ResponseEntity.noContent().build());
	}
}
