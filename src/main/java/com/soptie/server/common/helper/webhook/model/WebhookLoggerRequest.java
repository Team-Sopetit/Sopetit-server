package com.soptie.server.common.helper.webhook.model;

import lombok.Builder;

@Builder
public record WebhookLoggerRequest(
	String title,
	String content,
	WebhookType webhookType
) {

	public static WebhookLoggerRequest signUp(long count, long memberId) {
		return WebhookLoggerRequest.builder()
			.title(String.format("🎉 전체 회원 수: %s명", count))
			.content(String.format("[ID=%s] 새로운 회원이 가입했습니다.", memberId))
			.webhookType(WebhookType.SIGN_UP)
			.build();
	}

	public static WebhookLoggerRequest withdraw(long count) {
		return WebhookLoggerRequest.builder()
			.title(String.format("💬 전체 회원 수: %s명", count))
			.content("기존 회원이 탈퇴했습니다.")
			.webhookType(WebhookType.SIGN_UP)
			.build();
	}

	public static WebhookLoggerRequest error(Exception exception) {
		return WebhookLoggerRequest.builder()
			.title("🚨 서버 에러가 발생했습니다. 로그를 확인해주세요.")
			.content(exception.getMessage())
			.webhookType(WebhookType.SIGN_UP)
			.build();
	}
}
