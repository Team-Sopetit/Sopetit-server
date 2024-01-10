package com.soptie.server.auth.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorMessage {

    // auth
    EMPTY_ACCESS_TOKEN("액세스 토큰이 없습니다."),
    EMPTY_REFRESH_TOKEN("리프레시 토큰이 없습니다"),
    INVALID_TOKEN("유효하지 않은 토큰입니다"),
    MESSAGE_UNAUTHORIZED("유효하지 않은 토큰"),

    // member
    EMPTY_MEMBER("존재하지 않는 회원입니다."),
    DUPLICATE_USERNAME("이미 존재하는 닉네임입니다."),
    INVALID_MEMBER("유효하지 않은 회원입니다."),
    INVALID_USERNAME("유효하지 않은 닉네임입니다.");

    private final String message;
}
