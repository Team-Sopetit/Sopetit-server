package com.soptie.server.member.message;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

	/* 400 BAD_REQUEST : 잘못된 요청 */
	INACCESSIBLE_ROUTINE(BAD_REQUEST, "회원의 루틴이 아닙니다."),
	NOT_ENOUGH_COTTON(BAD_REQUEST, "솜뭉치가 부족합니다."),

	/* 404 NOT_FOUND : 자원을 찾을 수 없음 */
	INVALID_MEMBER(NOT_FOUND, "유효하지 않은 회원입니다."),
	NOT_EXIST_DOLL(NOT_FOUND, "인형을 가지고 있지 않은 회원입니다"),

	/* 409 CONFLICT : 중복된 데이터 존재 */
	EXIST_PROFILE(CONFLICT, "프로필이 이미 존재합니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
