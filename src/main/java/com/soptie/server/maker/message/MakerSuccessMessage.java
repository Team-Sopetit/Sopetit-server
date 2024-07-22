package com.soptie.server.maker.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MakerSuccessMessage {

	SUCCESS_GET_MAKER_THEME("메이커 테마 조회 성공");

	private final String message;
}
