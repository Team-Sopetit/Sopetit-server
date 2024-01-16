package com.soptie.server.routine.exception;

import com.soptie.server.routine.message.ErrorCode;

import lombok.Getter;

@Getter
public class RoutineException extends RuntimeException {

	private final ErrorCode errorCode;

	public RoutineException(ErrorCode errorCode) {
		super("[RoutineException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
