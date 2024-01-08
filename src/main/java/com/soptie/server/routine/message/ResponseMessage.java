package com.soptie.server.routine.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessage {
	SUCCESS_GET_THEMES("테마 조회 성공"),
	;

	private final String message;
}
