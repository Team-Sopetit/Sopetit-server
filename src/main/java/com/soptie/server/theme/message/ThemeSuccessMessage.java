package com.soptie.server.theme.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ThemeSuccessMessage {

	SUCCESS_GET_THEME("데일리 루틴 테마 조회 성공"),
	SUCCESS_GET_HAPPINESS_THEME("행복 루틴 테마 조회 성공"),
	;

	private final String message;
}
