package com.soptie.server.api.controller.generic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessMessage {
	/* theme */
	GET_THEME("테마 조회 성공"),

	/* routine */
	CREATE_ROUTINE("루틴 추가 성공"),
	GET_ROUTINE("루틴 조회 성공"),
	ACHIEVE_ROUTINE("루틴 달성 성공"),
	DELETE_ROUTINE("루틴 삭제 성공"),

	/* challenge */
	GET_CHALLENGE("도전루틴 조회 성공");

	private final String message;
}
