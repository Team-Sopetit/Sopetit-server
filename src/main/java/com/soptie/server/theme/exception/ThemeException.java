package com.soptie.server.theme.exception;

import com.soptie.server.theme.message.ThemeErrorCode;

import lombok.Getter;

@Getter
public class ThemeException extends RuntimeException {

	private final ThemeErrorCode errorCode;

	public ThemeException(ThemeErrorCode errorCode) {
		super("[ThemeException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
