package com.soptie.server.common.helper.webhook.model;

import java.util.Collections;
import java.util.List;

import com.soptie.server.common.utils.LoggerUtils;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record DiscordRequest(
	String content,
	List<Embed> embeds
) {

	public static DiscordRequest from(@NonNull WebhookLoggerRequest request) {
		Embed embed = Embed.builder()
			.title(request.title())
			.description(LoggerUtils.createDescription(request.content()))
			.build();

		return DiscordRequest.builder()
			.content(String.format("# %s", request.webhookType().getName()))
			.embeds(Collections.singletonList(embed))
			.build();
	}

	@Builder
	private record Embed(
		String title,
		String description
	) {
	}
}
