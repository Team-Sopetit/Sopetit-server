package com.soptie.server.api.controller.dto.response.auth;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TokenGetResponse(
	@NonNull String accessToken
) {

	public static TokenGetResponse from(String accessToken) {
		return TokenGetResponse.builder()
			.accessToken(accessToken)
			.build();
	}
}
