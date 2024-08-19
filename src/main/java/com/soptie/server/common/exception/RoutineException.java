package com.soptie.server.common.exception;

import com.soptie.server.common.message.RoutineErrorCode;

import lombok.Getter;

@Getter
public class RoutineException extends RuntimeException {

	private final RoutineErrorCode errorCode;

	public RoutineException(RoutineErrorCode errorCode) {
		super("[RoutineException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
