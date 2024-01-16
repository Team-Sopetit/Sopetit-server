package com.soptie.server.common.dto;

import lombok.NonNull;

public record Response(
	boolean success,
	@NonNull String message,
	Object data
) {

	public static Response success(String message, Object data) {
		return new Response(true, message, data);
	}

	public static Response success(String message) {
		return new Response(true, message, null);
	}

	public static Response fail(String message) {
		return new Response(false, message, null);
	}
}
