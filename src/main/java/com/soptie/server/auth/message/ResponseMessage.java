package com.soptie.server.auth.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessage {

    SUCCESS_SIGNIN("소셜로그인 성공");

    private final String message;
}
