package com.soptie.server.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ExceptionCode {
	/* 4xx */
	NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 리소스");

	private final HttpStatus httpStatus;
	private final String message;
}
