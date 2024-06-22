package com.soptie.server.auth.service.dto.request;

import static lombok.AccessLevel.*;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record TokenGetServiceRequest(
	@NonNull String refreshToken
) {

	public static TokenGetServiceRequest of(String refreshToken) {
		return TokenGetServiceRequest.builder()
			.refreshToken(refreshToken)
			.build();
	}
}
