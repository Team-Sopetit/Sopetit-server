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

	public static NotificationRequest createMorningAlarm(String token) {
		return NotificationRequest.builder()
			.targetToken(token)
			.title("안녕! 좋은 아침이야")
			.body("오늘도 함께 루틴을 시작해볼까?")
			.build();
	}

	public static NotificationRequest createNightAlarm(String token) {
		return NotificationRequest.builder()
			.targetToken(token)
			.title("꼬르륵.. 나 배고파")
			.body("함께 남은 루틴을 달성해볼까?\n나 솜뭉치 챙겨주는 것도 잊지마!")
			.build();
	}

	public static NotificationRequest createRoutineAlarm(String token, String routine) {
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
