package com.soptie.server.common.message;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthErrorCode {

	/* 401 UNAUTHORIZED : 권한 없음 */
	INVALID_TOKEN(UNAUTHORIZED, "유효하지 않은 토큰입니다."),
	INVALID_KEY(UNAUTHORIZED, "유효하지 않은 키입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
