package com.soptie.server.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "logging")
public record LoggingProperties(
	WebhookUrl webhook_url
) {

	public record WebhookUrl(
		String sign_up,
		String withdraw,
		String error
	) {
	}
}
