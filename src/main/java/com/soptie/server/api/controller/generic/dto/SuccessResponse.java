package com.soptie.server.api.controller.generic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record SuccessResponse<T>(
	boolean success,
	@NotNull String message,
	@JsonInclude(JsonInclude.Include.NON_NULL) T data
) implements BaseResponse {

	private static final String SUCCESS_RESPONSE_MESSAGE = "요청 처리 성공";

	public static <T> SuccessResponse<T> from() {
		return SuccessResponse.<T>builder()
			.success(true)
			.message(SUCCESS_RESPONSE_MESSAGE)
			.build();
	}

	public static <T> SuccessResponse<T> from(T data) {
		return SuccessResponse.<T>builder()
			.success(true)
			.message(SUCCESS_RESPONSE_MESSAGE)
			.data(data)
			.build();
	}

	public static <T> SuccessResponse<T> success(String message, T data) {
		return SuccessResponse.<T>builder()
			.success(true)
			.message(message)
			.data(data)
			.build();
	}

	public static SuccessResponse<?> success(String message) {
		return SuccessResponse.builder()
			.success(true)
			.message(message)
			.build();
	}
}
