package com.soptie.server.auth.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessage {

    SUCCESS_SIGN_IN("소셜로그인 성공"),
    SUCCESS_SIGN_OUT("로그아웃 성공"),
    SUCCESS_WITHDRAWAL("회원 탈퇴 성공"),
    ;

    private final String message;
}
