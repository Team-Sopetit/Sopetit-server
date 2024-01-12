package com.soptie.server.member.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResponseMessage {

    SUCCESS_CREATE_PROFILE("프로필 생성 성공"),
    SUCCESS_GIVE_COTTON("솜뭉치 주기 성공")
    ;

    private final String message;
}
