package com.soptie.server.member.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {
	EMPTY_MEMBER("존재하지 않는 회원입니다."),
	DUPLICATE_USERNAME("이미 존재하는 닉네임입니다."),
	INVALID_MEMBER("유효하지 않은 회원입니다."),
	INVALID_USERNAME("유효하지 않은 닉네임입니다."),
	INACCESSIBLE_ROUTINE("회원의 루틴이 아닙니다."),
	EXIST_PROFILE("프로필이 이미 존재합니다."),
	NOT_ENOUGH_COTTON("솜뭉치가 부족합니다.")
	;

	private final String message;
}
