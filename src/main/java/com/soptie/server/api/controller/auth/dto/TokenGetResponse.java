package com.soptie.server.api.controller.auth.dto;

import static lombok.AccessLevel.*;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record TokenGetResponse(
	@NotNull String accessToken
) {

	public static TokenGetResponse from(String accessToken) {
		return TokenGetResponse.builder()
			.accessToken(accessToken)
			.build();
	}
}
