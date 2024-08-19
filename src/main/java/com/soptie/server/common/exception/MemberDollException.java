package com.soptie.server.common.exception;

import com.soptie.server.common.message.DollErrorCode;

import lombok.Getter;

@Getter
public class MemberDollException extends RuntimeException {

	private final DollErrorCode errorCode;

	public MemberDollException(DollErrorCode errorCode) {
		super("[MemberDollException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
