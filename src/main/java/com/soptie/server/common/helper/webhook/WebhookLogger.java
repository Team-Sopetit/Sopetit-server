package com.soptie.server.common.helper.webhook;

import com.soptie.server.common.helper.webhook.model.WebhookLoggerRequest;

public interface WebhookLogger {

	void send(WebhookLoggerRequest request);
}
