package com.soptie.server.api.controller.dto.response;

import lombok.NonNull;

public interface BaseResponse {
	boolean success();

	@NonNull
	String message();
}
