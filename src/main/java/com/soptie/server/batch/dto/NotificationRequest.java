package com.soptie.server.batch.dto;

import static lombok.AccessLevel.PRIVATE;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record NotificationRequest(
	@NotNull String targetToken,
	@NotNull String title,
	@NotNull String body
) {

	public static NotificationRequest of(String token, String routine) {
		return NotificationRequest.builder()
			.targetToken(token)
			.title("루틴 시작")
			.body(createBody(routine))
			.build();
	}

	private static String createBody(String routine) {
		return "'" + routine + "' 딱 하기 좋은 시간!";
	}

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
