package com.soptie.server.common.dto;

import lombok.NonNull;

public interface BaseResponse {
	boolean success();

	@NonNull
	String message();
}
