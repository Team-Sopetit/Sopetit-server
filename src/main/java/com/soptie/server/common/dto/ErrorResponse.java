package com.soptie.server.common.dto;

import static lombok.AccessLevel.*;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record ErrorResponse(
	boolean success,
	@NonNull String message
) implements BaseResponse {

	public static ErrorResponse of(String message) {
		return ErrorResponse.builder()
			.success(false)
			.message(message)
			.build();
	}
}
