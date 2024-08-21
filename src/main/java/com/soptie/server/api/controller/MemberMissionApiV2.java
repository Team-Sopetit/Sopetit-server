package com.soptie.server.api.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.MemberMissionApiV2Docs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.membermission.MemberMissionService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/challenge/member")
public class MemberMissionApiV2 implements MemberMissionApiV2Docs {
	private final MemberMissionService memberMissionService;

	@GetMapping
	public ResponseEntity<?> getMemberMission(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		return memberMissionService.getMemberMission(memberId)
			.map(response ->
				ResponseEntity.ok(SuccessResponse.success(SuccessMessage.GET_ROUTINE.getMessage(), response)))
			.orElseGet(() -> ResponseEntity.noContent().build());
	}
}
