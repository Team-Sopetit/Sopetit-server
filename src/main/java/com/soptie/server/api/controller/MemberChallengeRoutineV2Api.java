package com.soptie.server.api.controller;

import static com.soptie.server.common.message.MemberRoutineSuccessMassage.*;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.MemberChallengeRoutineV2ApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.memberroutine.MemberChallengeRoutineAcquireResponseV2;
import com.soptie.server.domain.memberroutine.MemberChallengeRoutineAcquireServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/challenge/member")
public class MemberChallengeRoutineV2Api implements MemberChallengeRoutineV2ApiDocs {

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
