package com.soptie.server.api.controller.generic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessMessage {
	/* theme */
	GET_THEME("테마 조회 성공"),

	/* routine */
	GET_ROUTINE("루틴 조회 성공");

	private final String message;
}
