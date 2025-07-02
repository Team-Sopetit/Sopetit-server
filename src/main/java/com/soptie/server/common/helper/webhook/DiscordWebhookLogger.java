package com.soptie.server.common.helper.webhook;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.soptie.server.common.helper.webhook.model.DiscordRequest;
import com.soptie.server.common.helper.webhook.model.WebhookLoggerRequest;
import com.soptie.server.common.utils.LoggerUtils;
import com.soptie.server.config.properties.LoggingProperties;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiscordWebhookLogger implements WebhookLogger {

	private final LoggingProperties loggingProperties;

	private static final String APPLICATION_JSON_UTF8_VALUE = "application/json; UTF-8";

	@Override
	public void send(@NonNull WebhookLoggerRequest request) {
		try {
			RestClient.create()
				.post()
				.uri(LoggerUtils.getWebhookUrl(loggingProperties, request.webhookType()))
				.header(HttpHeaders.ACCEPT, APPLICATION_JSON_UTF8_VALUE)
				.header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE)
				.body(DiscordRequest.from(request))
				.retrieve();
		} catch (Exception e) {
			log.error("[DiscordWebhookLogger][send] failed to send request={}", request, e);
		}
	}
}
