package com.soptie.server.auth.message;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

	/* 400 BAD_REQUEST : 잘못된 요청 */
	INVALID_TOKEN(BAD_REQUEST, "유효하지 않은 토큰입니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
