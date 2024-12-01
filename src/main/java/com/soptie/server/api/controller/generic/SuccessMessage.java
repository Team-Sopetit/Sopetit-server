package com.soptie.server.api.controller.generic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessMessage {
	/* auth */
	SUCCESS_SIGN_IN("로그인 성공"),
	SUCCESS_RECREATE_TOKEN("토큰 재발급 성공"),
	SUCCESS_SIGN_OUT("로그아웃 성공"),
	SUCCESS_WITHDRAWAL("회원 탈퇴 성공"),

	/* member */
	CREATE_MEMBER_PROFILE("회원 프로필 등록 성공"),
	GET_MEMBER_PROFILE("회원 프로필 조회 성공"),
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
	GET_CHALLENGE("도전루틴 조회 성공"),

	/* maker */
	SUCCESS_GET_MAKER_THEME("메이커 테마 조회 성공"),

	/* memo */
	CREATE_MEMO("메모 생성 성공"),
	UPDATE_MEMO("메모 수정 성공"),
	DELETE_MEMO("메모 삭제 성공"),

	/* version */
	GET_VERSION("버전 조회 성공");

	private final String message;
}
