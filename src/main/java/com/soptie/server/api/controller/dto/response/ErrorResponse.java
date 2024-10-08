package com.soptie.server.api.controller.dto.response;

import static lombok.AccessLevel.*;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record ErrorResponse(
	boolean success,
	@NotNull String message
) implements BaseResponse {

	public static ErrorResponse of(String message) {
		return ErrorResponse.builder()
			.success(false)
			.message(message)
			.build();
	}
}
