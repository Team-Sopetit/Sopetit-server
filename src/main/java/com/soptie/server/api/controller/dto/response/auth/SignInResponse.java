package com.soptie.server.api.controller.dto.response.auth;

import static lombok.AccessLevel.PRIVATE;

import com.soptie.server.domain.auth.Token;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record SignInResponse(
	@Schema(description = "액세스 토큰")
	@NotNull String accessToken,
	@Schema(description = "리프레시 토큰")
	@NotNull String refreshToken,
	@Schema(description = "멤버 인형 존재 여부", example = "true")
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
