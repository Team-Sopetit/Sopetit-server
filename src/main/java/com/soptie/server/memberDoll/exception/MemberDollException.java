package com.soptie.server.memberDoll.exception;

import com.soptie.server.doll.message.ErrorCode;
import lombok.Getter;

@Getter
public class MemberDollException extends RuntimeException {

    private final ErrorCode errorCode;

    public MemberDollException(ErrorCode errorCode) {
        super("[MemberDollException] : " + errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
