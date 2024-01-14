package com.soptie.server.routine.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
	INVALID_THEME("유효하지 않은 테마입니다."),
	INVALID_ROUTINE("유효하지 않은 루틴입니다."),
	CANNOT_ADD_MEMBER_ROUTINE("더 이상 루틴을 추가할 수 없는 회원입니다."),
	DUPLICATED_ROUTINE("이미 추가한 루틴입니다."),
	;

	private final String message;
}
