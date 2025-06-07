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
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class RoutineAlarmScheduler {

	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberAdapter memberAdapter;
	private final FirebaseMessaging firebaseMessaging;

	// 1분에 한 번씩 돌도록 설정
	@Scheduled(cron = "${batch.cron.init.alarm}")
	public void sendRoutineAlarm() {
		LocalTime alarmTime = LocalTime.now()
			.withSecond(0)
			.withNano(0);

		List<MemberRoutine> routines = memberRoutineAdapter.findByAlarmTime(alarmTime);

		for (MemberRoutine routine : routines) {
			val member = memberAdapter.findById(routine.getMemberId());
			if (member.getFcmToken() == null) {
				continue;
			}
			// MemberRoutine Content에 루틴 명 넣어주는 처리 프로세스 필요.
			val request = NotificationRequest.of(member.getFcmToken(), routine.getContent());
			sendMessage(request);
		}
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
