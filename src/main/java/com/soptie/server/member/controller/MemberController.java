package com.soptie.server.member.controller;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.common.support.UriGenerator.*;
import static com.soptie.server.member.message.MemberSuccessMessage.*;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.member.controller.docs.MemberApi;
import com.soptie.server.member.controller.dto.request.MemberProfileCreateRequest;
import com.soptie.server.member.controller.dto.response.MemberCottonCountGetResponse;
import com.soptie.server.member.controller.dto.response.MemberHomeInfoGetResponse;
import com.soptie.server.member.entity.CottonType;
import com.soptie.server.member.service.MemberService;
import com.soptie.server.member.service.dto.request.CottonGiveServiceRequest;
import com.soptie.server.member.service.dto.request.MemberHomeInfoGetServiceRequest;
import com.soptie.server.member.service.dto.request.MemberProfileCreateServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController implements MemberApi {

	private final MemberService memberService;

	@PostMapping
	public ResponseEntity<BaseResponse> createMemberProfile(
		Principal principal,
		@RequestBody MemberProfileCreateRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		memberService.createMemberProfile(MemberProfileCreateServiceRequest.of(memberId, request));
		return ResponseEntity.created(getUri("/")).body(success(SUCCESS_CREATE_PROFILE.getMessage()));
	}

	@PatchMapping("/cotton/{cottonType}")
	public ResponseEntity<SuccessResponse<MemberCottonCountGetResponse>> giveCotton(
		Principal principal,
		@PathVariable CottonType cottonType
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberCottonCountGetResponse.of(
			memberService.giveCotton(CottonGiveServiceRequest.of(memberId, cottonType)));
		return ResponseEntity.ok(success(SUCCESS_GIVE_COTTON.getMessage(), response));
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<MemberHomeInfoGetResponse>> getMemberHomeInfo(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = MemberHomeInfoGetResponse.of(
			memberService.getMemberHomeInfo(MemberHomeInfoGetServiceRequest.of(memberId)));
		return ResponseEntity.ok(success(SUCCESS_HOME_INFO.getMessage(), response));
	}
}
