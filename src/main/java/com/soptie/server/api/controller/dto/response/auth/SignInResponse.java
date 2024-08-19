package com.soptie.server.api.controller.dto.response.auth;

import static lombok.AccessLevel.*;

import com.soptie.server.domain.auth.SignInServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record SignInResponse(
	@NonNull String accessToken,
	@NonNull String refreshToken,
	boolean isMemberDollExist
) {

	public static SignInResponse of(SignInServiceResponse response) {
		return SignInResponse.builder()
			.accessToken(response.accessToken())
			.refreshToken(response.refreshToken())
			.isMemberDollExist(response.isMemberDollExist())
			.build();
	}
}
