package com.soptie.server.api.controller.generic.dto;

import static com.soptie.server.common.support.ValueConfig.*;
import static lombok.AccessLevel.*;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record ErrorResponse(
	boolean success,
	@NotNull String message,
	@NotNull String data
) implements BaseResponse {

	public static ErrorResponse of(String message) {
		return ErrorResponse.builder()
			.success(false)
			.message(message)
			.data(BLANK)
			.build();
	}
}
