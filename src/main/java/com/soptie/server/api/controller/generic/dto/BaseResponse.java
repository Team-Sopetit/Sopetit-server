package com.soptie.server.api.controller.generic.dto;

import lombok.NonNull;

public interface BaseResponse {
	boolean success();

	@NonNull
	String message();
}
