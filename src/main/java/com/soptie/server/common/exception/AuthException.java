package com.soptie.server.common.exception;

import com.soptie.server.common.message.AuthErrorCode;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

	private final AuthErrorCode errorCode;

	public AuthException(AuthErrorCode errorCode) {
		super("[AuthException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
