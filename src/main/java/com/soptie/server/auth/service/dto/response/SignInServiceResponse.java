package com.soptie.server.auth.service.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.auth.vo.Token;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record SignInServiceResponse(
	@NonNull String accessToken,
	@NonNull String refreshToken,
	boolean isMemberDollExist
) {

	public static SignInServiceResponse of(Token token, boolean isMemberDollExist) {
		return SignInServiceResponse.builder()
			.accessToken(token.getAccessToken())
			.refreshToken(token.getRefreshToken())
			.isMemberDollExist(isMemberDollExist)
			.build();
	}
}
