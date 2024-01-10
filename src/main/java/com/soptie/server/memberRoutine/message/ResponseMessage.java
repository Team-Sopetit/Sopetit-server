package com.soptie.server.memberRoutine.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessage {
	SUCCESS_CREATE_ROUTINE("데일리 루틴 추가 성공"),
	;

	private final String message;
}
