package com.soptie.server.common.exception;

import com.soptie.server.common.message.DollErrorCode;

import lombok.Getter;

@Getter
public class DollException extends RuntimeException {

	private final DollErrorCode errorCode;

	public DollException(DollErrorCode errorCode) {
		super("[DollException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
