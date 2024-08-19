package com.soptie.server.domain.auth;

import static lombok.AccessLevel.*;

import com.soptie.server.api.controller.dto.request.auth.SignInRequest;

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
