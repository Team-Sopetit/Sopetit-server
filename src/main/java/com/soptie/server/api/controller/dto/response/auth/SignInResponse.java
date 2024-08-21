package com.soptie.server.api.controller.dto.response.auth;

import static lombok.AccessLevel.PRIVATE;

import com.soptie.server.domain.auth.Token;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record SignInResponse(
	@NonNull String accessToken,
	@NonNull String refreshToken,
	boolean isMemberDollExist
) {

	public static SignInResponse of(Token token, boolean isMemberDollExist) {
		return SignInResponse.builder()
			.accessToken(token.getAccessToken())
			.refreshToken(token.getRefreshToken())
			.isMemberDollExist(isMemberDollExist)
			.build();
	}
}
