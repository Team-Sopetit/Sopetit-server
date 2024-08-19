package com.soptie.server.common.exception;

import com.soptie.server.common.message.DollErrorCode;

import lombok.Getter;

@Getter
public class MakerException extends RuntimeException {

	private final DollErrorCode errorCode;

	public MakerException(DollErrorCode errorCode) {
		super("[MakerException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
