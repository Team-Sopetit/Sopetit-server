package com.soptie.server.member.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
	INVALID_MEMBER("유효하지 않은 회원입니다."),
	INACCESSIBLE_ROUTINE("해당 회원이 접근할 수 없는 루틴입니다."),
	;

	private final String meesage;
}
