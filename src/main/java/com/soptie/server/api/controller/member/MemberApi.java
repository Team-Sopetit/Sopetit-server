package com.soptie.server.api.controller.member;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.common.constants.MessageConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.member.docs.MemberApiDocs;
import com.soptie.server.api.controller.member.dto.CreateProfileRequest;
import com.soptie.server.api.controller.member.dto.GetHomeInfoResponse;
import com.soptie.server.api.controller.member.dto.GiveMemberCottonResponse;
import com.soptie.server.api.controller.member.dto.MemberProfileResponse;
import com.soptie.server.domain.member.CottonType;
import com.soptie.server.domain.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApi implements MemberApiDocs {

	private final MemberService memberService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public SuccessResponse<?> createMemberProfile(
		Principal principal,
		@RequestBody CreateProfileRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		memberService.createMemberProfile(memberId, request);
		return SuccessResponse.success(MessageConstants.CREATE_MEMBER_PROFILE.getMessage());
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/cotton/{cottonType}")
	public SuccessResponse<GiveMemberCottonResponse> giveCotton(
		Principal principal,
		@PathVariable CottonType cottonType
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberService.giveCotton(memberId, cottonType);
		return SuccessResponse.success(MessageConstants.GIVE_COTTON.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public SuccessResponse<GetHomeInfoResponse> getMemberHomeInfo(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberService.getMemberHomeInfo(memberId);
		return SuccessResponse.success(MessageConstants.GET_MEMBER_HOME.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/profile")
	public SuccessResponse<MemberProfileResponse> getMemberProfile(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberService.getMemberProfile(memberId);
		return SuccessResponse.success(MessageConstants.GET_MEMBER_PROFILE.getMessage(), response);
	}

}
