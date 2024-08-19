package com.soptie.server.api.controller.dto.response.auth;

import static lombok.AccessLevel.*;

import com.soptie.server.domain.auth.TokenGetServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TokenGetResponse(
	@NonNull String accessToken
) {

	public static TokenGetResponse of(TokenGetServiceResponse response) {
		return TokenGetResponse.builder()
			.accessToken(response.accessToken())
			.build();
	}
}
