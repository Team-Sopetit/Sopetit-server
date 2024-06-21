package com.soptie.server.auth.service.dto.response;

import static lombok.AccessLevel.*;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TokenGetServiceResponse(
	@NonNull String accessToken
) {

	public static TokenGetServiceResponse of(String accessToken) {
		return TokenGetServiceResponse.builder()
			.accessToken(accessToken)
			.build();
	}
}
