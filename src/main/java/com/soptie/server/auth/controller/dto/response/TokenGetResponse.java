package com.soptie.server.auth.controller.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.auth.service.dto.response.TokenGetServiceResponse;

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
