package com.soptie.server.api.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.MemberChallengeApiDocs;
import com.soptie.server.api.controller.dto.request.membermission.CreateMemberChallengeRequest;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.membermission.CreateMemberChallengeResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.membermission.MemberChallengeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/members/challenges")
public class MemberChallengeApi implements MemberChallengeApiDocs {
	private final MemberChallengeService memberChallengeService;

	@GetMapping
	public ResponseEntity<?> getMemberChallenge(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		return memberChallengeService.getMemberChallenge(memberId)
			.map(response ->
				ResponseEntity.ok(SuccessResponse.success(SuccessMessage.GET_ROUTINE.getMessage(), response)))
			.orElseGet(() -> ResponseEntity.noContent().build());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public SuccessResponse<CreateMemberChallengeResponse> createMemberChallenge(
		Principal principal,
		@RequestBody CreateMemberChallengeRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberChallengeService.createMemberChallenge(memberId, request);
		return SuccessResponse.success(SuccessMessage.CREATE_ROUTINE.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping
	public SuccessResponse<?> deleteMemberChallenge(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		memberChallengeService.deleteMemberChallenge(memberId);
		return SuccessResponse.success(SuccessMessage.DELETE_ROUTINE.getMessage());
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/achievement")
	public SuccessResponse<?> achieveMemberChallenge(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		memberChallengeService.achieveMemberChallenge(memberId);
		return SuccessResponse.success(SuccessMessage.ACHIEVE_ROUTINE.getMessage());
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/history/{historyId}")
	public SuccessResponse<?> deleteHistory(@PathVariable long historyId) {
		memberChallengeService.deleteHistory(historyId);
		return SuccessResponse.success(SuccessMessage.DELETE_ROUTINE.getMessage());
	}
}
