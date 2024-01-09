package com.soptie.server.doll.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
	INVALID_TYPE("유효하지 않은 인형 타입입니다."),
	;

	private final String message;
}
