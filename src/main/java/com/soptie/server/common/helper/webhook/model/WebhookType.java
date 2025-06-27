package com.soptie.server.common.helper.webhook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebhookType {
	SIGN_UP("회원가입 알림"),
	WITHDRAW("회원탈퇴 알림"),
	ERROR("에러 알림");

	private final String name;
}
