package com.soptie.server.common.exception;

import com.soptie.server.common.message.ThemeErrorCode;

import lombok.Getter;

@Getter
public class ThemeException extends RuntimeException {

	private final ThemeErrorCode errorCode;

	public ThemeException(ThemeErrorCode errorCode) {
		super("[ThemeException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
