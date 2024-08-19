package com.soptie.server.domain.auth;

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
