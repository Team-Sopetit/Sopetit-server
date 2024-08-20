package com.soptie.server.api.controller.generic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessMessage {
	/* member */
	CREATE_MEMBER_PROFILE("회원 프로필 등록 성공"),
	GIVE_COTTON("솜뭉치 주기 성공"),
	GET_MEMBER_HOME("홈화면 정보 조회 성공"),

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
