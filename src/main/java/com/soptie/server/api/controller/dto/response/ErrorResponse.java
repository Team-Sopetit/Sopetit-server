package com.soptie.server.api.controller.dto.response;

import static com.soptie.server.common.support.ValueConfig.BLANK;
import static lombok.AccessLevel.PRIVATE;

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
