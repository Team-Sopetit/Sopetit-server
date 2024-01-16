package com.soptie.server.doll.exception;

import com.soptie.server.doll.message.ErrorCode;

import lombok.Getter;

@Getter
public class DollException extends RuntimeException {

	private final ErrorCode errorCode;

	public DollException(ErrorCode errorCode) {
		super("[DollException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
