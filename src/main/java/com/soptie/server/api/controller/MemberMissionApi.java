package com.soptie.server.api.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.MemberMissionApiDocs;
import com.soptie.server.api.controller.dto.request.membermission.CreateMemberMissionRequest;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.membermission.CreateMemberMissionResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.membermission.MemberMissionService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness/member")
public class MemberMissionApi implements MemberMissionApiDocs {
	private MemberMissionService memberMissionService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public SuccessResponse<CreateMemberMissionResponse> createMemberMission(
		Principal principal,
		@RequestBody CreateMemberMissionRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberMissionService.createMemberMission(memberId, request);
		return SuccessResponse.success(SuccessMessage.CREATE_ROUTINE.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/routine/{routineId}")
	public SuccessResponse<?> deleteMemberMission(Principal principal, @PathVariable long routineId) {
		val memberId = Long.parseLong(principal.getName());
		memberMissionService.deleteMemberMission(memberId, routineId);
		return SuccessResponse.success(SuccessMessage.DELETE_ROUTINE.getMessage());
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/routine/{routineId}")
	public SuccessResponse<?> achieveMemberMission(Principal principal, @PathVariable long routineId) {
		val memberId = Long.parseLong(principal.getName());
		memberMissionService.achieveMemberMission(memberId, routineId);
		return SuccessResponse.success(SuccessMessage.ACHIEVE_ROUTINE.getMessage());
	}
}
