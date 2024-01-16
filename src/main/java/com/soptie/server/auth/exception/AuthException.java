package com.soptie.server.auth.exception;

import com.soptie.server.auth.message.ErrorCode;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

	private final ErrorCode errorCode;

	public AuthException(ErrorCode errorCode) {
		super("[AuthException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
