package com.soptie.server.maker.exception;

import com.soptie.server.doll.message.ErrorCode;

import lombok.Getter;

@Getter
public class MakerException extends RuntimeException {

	private final ErrorCode errorCode;

	public MakerException(ErrorCode errorCode) {
		super("[MakerException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
