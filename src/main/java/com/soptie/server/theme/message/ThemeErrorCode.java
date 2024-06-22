package com.soptie.server.theme.message;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ThemeErrorCode {

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_THEME(NOT_FOUND, "유효하지 않은 테마입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
