package com.soptie.server.routine.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
	INVALID_THEME("유효하지 않은 테마입니다."),
	;

	private final String message;
}
