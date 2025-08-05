package com.soptie.server.common.helper.webhook.model;

import jakarta.servlet.http.HttpServletRequest;
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
			.webhookType(WebhookType.WITHDRAW)
			.build();
	}

	public static WebhookLoggerRequest error(Exception exception, HttpServletRequest request) {
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();

		String title = String.format("🚨 %s %s", method, uri);
		if (queryString != null) {
			title += "?" + queryString;
		}

		return WebhookLoggerRequest.builder()
			.title(title)
			.content(exception.getMessage())
			.webhookType(WebhookType.ERROR)
			.build();
	}
}
