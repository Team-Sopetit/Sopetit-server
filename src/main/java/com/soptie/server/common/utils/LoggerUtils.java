package com.soptie.server.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.soptie.server.common.helper.webhook.model.WebhookType;
import com.soptie.server.config.properties.LoggingProperties;

public class LoggerUtils {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String getWebhookUrl(LoggingProperties loggingProperties, WebhookType webhookType) {
		return switch (webhookType) {
			case SIGN_UP -> loggingProperties.webhook_url().sign_up();
			case WITHDRAW -> loggingProperties.webhook_url().withdraw();
			case ERROR -> loggingProperties.webhook_url().error();
		};
	}

	public static String createDescription(String content) {
		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
		return String.format("%s\n\n**%s**", now, content);
	}
}
