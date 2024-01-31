package com.soptie.server.routine.message;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

	/* 400 BAD_REQUEST : 잘못된 요청 */
	CANNOT_ADD_MEMBER_ROUTINE(BAD_REQUEST, "더 이상 루틴을 추가할 수 없는 회원입니다."),

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_THEME(NOT_FOUND, "유효하지 않은 테마입니다."),
	INVALID_ROUTINE(NOT_FOUND, "유효하지 않은 루틴입니다."),

	/* 409 CONFLICT : 중복된 데이터 존재 */
	DUPLICATED_ROUTINE(CONFLICT, "이미 추가한 루틴입니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
