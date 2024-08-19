package com.soptie.server.common.exception;

import com.soptie.server.common.message.MemberErrorCode;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

	private final MemberErrorCode errorCode;

	public MemberException(MemberErrorCode errorCode) {
		super("[MemberException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
