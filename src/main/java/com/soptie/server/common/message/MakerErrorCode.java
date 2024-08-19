package com.soptie.server.common.message;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MakerErrorCode {

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_MAKER(NOT_FOUND, "유효하지 않은 메이커입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
