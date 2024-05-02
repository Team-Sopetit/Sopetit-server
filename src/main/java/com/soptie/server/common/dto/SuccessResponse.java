package com.soptie.server.common.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record SuccessResponse<T>(
	boolean success,
	@NonNull String message,
	@JsonInclude(value = NON_NULL) T data
) implements BaseResponse {

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
