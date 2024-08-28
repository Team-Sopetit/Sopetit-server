package com.soptie.server.common.exception;

import lombok.Getter;

@Getter
public class SoftieException extends RuntimeException {
	private final int statusCode;
	private final String defaultMessage;
	private final String detailMessage;

	public SoftieException(ExceptionCode exceptionCode) {
		super("[" + exceptionCode.getHttpStatus() + "] " + exceptionCode.getMessage());
		this.statusCode = exceptionCode.getHttpStatus().value();
		this.defaultMessage = exceptionCode.getMessage();
		this.detailMessage = null;
	}

	public SoftieException(ExceptionCode exceptionCode, String detailMessage) {
		super("[" + exceptionCode.getHttpStatus() + "] " + exceptionCode.getMessage());
		this.statusCode = exceptionCode.getHttpStatus().value();
		this.defaultMessage = exceptionCode.getMessage();
		this.detailMessage = detailMessage;
	}
}
