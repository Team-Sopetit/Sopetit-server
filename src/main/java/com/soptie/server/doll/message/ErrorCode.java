package com.soptie.server.doll.message;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

	/* 400 BAD_REQUEST : 잘못된 요청 */
	INVALID_TYPE(BAD_REQUEST, "유효하지 않은 인형 타입입니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
