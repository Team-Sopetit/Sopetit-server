package com.soptie.server.batch.dto;

public class NotificationHelper {

	private static final String MORNING_ALARM_TITLE = "안녕! 좋은 아침이야";
	private static final String MORNING_ALARM_BODY = "오늘도 함께 루틴을 시작해볼까?";
	private static final String NIGHT_ALARM_TITLE = "꼬르륵.. 나 배고파";
	private static final String NIGHT_ALARM_BODY = "함께 남은 루틴을 달성해볼까?\n나 솜뭉치 챙겨주는 것도 잊지마!";
	private static final String INACTIVE_MEMBER_ALARM_TITLE = "잘 지내고 있지?";
	private static final String INACTIVE_MEMBER_ALARM_BODY = "나 혼자 루틴하려고 했는데... 역시 너랑 같이해야 재밌어! 오늘도 함께 해볼까?";
	private static final String ROUTINE_ALARM_TITLE = "루틴 시작";
	private static final String ROUTINE_ALARM_BODY_PREFIX = "'";
	private static final String ROUTINE_ALARM_BODY_SUFFIX = "' 딱 하기 좋은 시간!";

	public static NotificationRequest createMorningAlarm(String token) {
		return NotificationRequest.builder()
			.targetToken(token)
			.title(MORNING_ALARM_TITLE)
			.body(MORNING_ALARM_BODY)
			.build();
	}

	public static NotificationRequest createNightAlarm(String token) {
		return NotificationRequest.builder()
			.targetToken(token)
			.title(NIGHT_ALARM_TITLE)
			.body(NIGHT_ALARM_BODY)
			.build();
	}

	public static NotificationRequest createInactiveMemberAlarm(String token) {
		return NotificationRequest.builder()
			.targetToken(token)
			.title(INACTIVE_MEMBER_ALARM_TITLE)
			.body(INACTIVE_MEMBER_ALARM_BODY)
			.build();
	}

	public static NotificationRequest createRoutineAlarm(String token, String routine) {
		return NotificationRequest.builder()
			.targetToken(token)
			.title(ROUTINE_ALARM_TITLE)
			.body(createBody(routine))
			.build();
	}

	private static String createBody(String routine) {
		return ROUTINE_ALARM_BODY_PREFIX + routine + ROUTINE_ALARM_BODY_SUFFIX;
	}
}
