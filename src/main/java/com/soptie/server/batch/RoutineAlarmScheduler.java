package com.soptie.server.batch;

import java.time.LocalTime;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.FirebaseMessaging;
import com.soptie.server.batch.dto.NotificationRequest;
import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memberroutine.RoutineAlarm;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAlarmAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class RoutineAlarmScheduler {

	private final RoutineAlarmAdapter routineAlarmAdapter;
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberAdapter memberAdapter;
	private final FirebaseMessaging firebaseMessaging;

	@Scheduled(cron = "0 0 10 * * *")
	void sendMorningAlarm() {
		val targets = getAlarmTargets();
		for (val member : targets) {
			val token = member.getFcmToken();
			val request = NotificationRequest.createMorningAlarm(token);
			sendMessage(request);
		}
	}

	@Scheduled(cron = "0 0 20 * * *")
	void sendNightAlarm() {
		val targets = getAlarmTargets();
		for (val member : targets) {
			val token = member.getFcmToken();
			val request = NotificationRequest.createNightAlarm(token);
			sendMessage(request);
		}
	}

	// 10분에 한 번씩 돌도록 설정
	@Scheduled(cron = "${batch.cron.init.alarm}")
	public void sendRoutineAlarm() {
		LocalTime alarmTime = LocalTime.now()
			.withSecond(0)
			.withNano(0);

		List<RoutineAlarm> alarms = routineAlarmAdapter.findAllByAlarmTime(alarmTime);

		for (RoutineAlarm alarm : alarms) {
			val member = memberAdapter.findById(alarm.getMemberId());
			val routine = memberRoutineAdapter.findById(alarm.getMemberRoutineId());
			val request = NotificationRequest.createRoutineAlarm(member.getFcmToken(), routine.getContent());
			sendMessage(request);
		}
	}

	// 루틴 알람이 존재하지 않고 fcm token이 존재하는 유저 리스트 반환
	private List<Member> getAlarmTargets() {
		val members = memberAdapter.findAllByFcmTokenIsNotNull();
		val alarms = routineAlarmAdapter.findAll().stream().map(RoutineAlarm::getMemberId).toList();
		return members.stream()
			.filter(member -> !alarms.contains(member.getId()))
			.toList();
	}

	private void sendMessage(final NotificationRequest request) {
		try {
			val message = request.buildMessage().setApnsConfig(getApnsConfig(request)).build();
			firebaseMessaging.sendAsync(message);
		} catch (RuntimeException exception) {
			throw new SoftieException(ExceptionCode.FCM_SERVICE_UNAVAILABLE, exception.getMessage());
		}
	}

	private ApnsConfig getApnsConfig(NotificationRequest request) {
		val alert = ApsAlert.builder().setTitle(request.title()).setBody(request.body()).build();
		val aps = Aps.builder().setAlert(alert).setSound("default").build();
		return ApnsConfig.builder().setAps(aps).build();
	}
}
