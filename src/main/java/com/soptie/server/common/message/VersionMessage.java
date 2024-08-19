package com.soptie.server.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VersionMessage {

	SUCCESS_GET_APP_VERSION("버전 정보 조회 성공");

	private final String message;
}
