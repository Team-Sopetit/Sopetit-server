package com.soptie.server.auth.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {

    EMPTY_ACCESS_TOKEN("액세스 토큰이 없습니다."),
    EMPTY_REFRESH_TOKEN("리프레시 토큰이 없습니다"),
    INVALID_TOKEN("유효하지 않은 토큰입니다"),
    MESSAGE_UNAUTHORIZED("유효하지 않은 토큰"),
    ;

    private final String message;
}
