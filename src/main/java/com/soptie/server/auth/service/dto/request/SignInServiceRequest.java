package com.soptie.server.auth.service.dto.request;

import static lombok.AccessLevel.*;

import com.soptie.server.auth.controller.dto.request.SignInRequest;
import com.soptie.server.member.entity.SocialType;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record SignInServiceRequest(
	@NonNull String socialAccessToken,
	@NonNull SocialType socialType
) {

	public static SignInServiceRequest of(String socialAccessToken, SignInRequest request) {
		return SignInServiceRequest.builder()
			.socialAccessToken(socialAccessToken)
			.socialType(request.socialType())
			.build();
	}
}
