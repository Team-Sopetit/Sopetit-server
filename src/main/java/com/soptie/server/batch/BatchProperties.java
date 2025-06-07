package com.soptie.server.batch;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "batch")
public record BatchProperties(
	Cron cron
) {

	public record Cron(
		Init init
	) {

		public record Init(
			String routine,
			String alarm
		) {
		}
	}
}
