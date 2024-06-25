package com.soptie.server.memberroutine2.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberRoutineSuccessMassage {
	SUCCESS_CREATE_ROUTINE("루틴 추가 성공"),
	SUCCESS_DELETE_ROUTINE("루틴 삭제 성공"),
	SUCCESS_ACHIEVE_ROUTINE("루틴 달성 성공"),
	SUCCESS_GET_ROUTINE("루틴 조회 성공");

	private final String message;
}
