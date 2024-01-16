package com.soptie.server.member.exception;

import com.soptie.server.auth.message.ErrorCode;

import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

	private final ErrorCode errorCode;

	public MemberException(ErrorCode errorCode) {
		super("[MemberException] : " + errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
