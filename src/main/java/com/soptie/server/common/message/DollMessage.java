package com.soptie.server.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DollMessage {

	SUCCESS_GET_IMAGE("인형 이미지 조회 성공");

	private final String message;
}
