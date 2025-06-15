package com.soptie.server.batch.dto;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NotificationRequest(
	@NotNull String targetToken,
	@NotNull String title,
	@NotNull String body
) {

	public Message.Builder buildMessage() {
		return Message.builder()
			.setToken(targetToken)
			.setNotification(toNotification());
	}

	public Notification toNotification() {
		return Notification.builder()
			.setTitle(title)
			.setBody(body)
			.build();
	}
}
