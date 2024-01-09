package com.soptie.server.routine.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessage {
	SUCCESS_GET_THEME("데일리 루틴 테마 조회 성공"),
	SUCCESS_GET_ROUTINE("데일리 루틴 조회 성공"),
	;

	private final String message;
}
