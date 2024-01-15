package com.soptie.server.routine.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessage {
	SUCCESS_GET_THEME("데일리 루틴 테마 조회 성공"),
	SUCCESS_GET_ROUTINE("데일리 루틴 조회 성공"),
	SUCCESS_GET_HAPPINESS_THEME("행복 루틴 테마 조회 성공"),
	SUCCESS_GET_HAPPINESS_ROUTINE("행복 루틴 조회 성공"),
	SUCCESS_GET_HAPPINESS_SUB_ROUTINES("행복 루틴 별 서브 루틴 리스트 조회 성공"),
	;

	private final String message;
}
