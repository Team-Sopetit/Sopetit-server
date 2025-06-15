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
import com.google.firebase.messaging.Message;
import com.soptie.server.batch.dto.NotificationHelper;
import com.soptie.server.batch.dto.NotificationRequest;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.memberroutine.RoutineAlarm;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAlarmAdapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class RoutineAlarmScheduler {

	private static final String DEFAULT = "default";

	private final RoutineAlarmAdapter routineAlarmAdapter;
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberAdapter memberAdapter;
	private final FirebaseMessaging firebaseMessaging;

	@Scheduled(cron = "0 0 10 * * *")
	void sendMorningAlarm() {
		val targets = getAlarmTargets();
		for (val member : targets) {
			String token = member.getFcmToken();
			NotificationRequest request = NotificationHelper.createMorningAlarm(token);
			sendMessage(request);
		}
	}

	@Scheduled(cron = "0 0 20 * * *")
	void sendNightAlarm() {
		List<Member> targets = getAlarmTargets();
		for (val member : targets) {
			String token = member.getFcmToken();
			NotificationRequest request = NotificationHelper.createNightAlarm(token);
			sendMessage(request);
		}
	}

	@Scheduled(cron = "0 */10 * * * *")
	public void sendRoutineAlarm() {
		LocalTime alarmTime = LocalTime.now()
			.withSecond(0)
			.withNano(0);

		List<RoutineAlarm> alarms = routineAlarmAdapter.findAllByAlarmTime(alarmTime);

		for (RoutineAlarm alarm : alarms) {
			Member member = memberAdapter.findById(alarm.getMemberId());
			MemberRoutine routine = memberRoutineAdapter.findById(alarm.getMemberRoutineId());
			NotificationRequest request = NotificationHelper.createRoutineAlarm(member.getFcmToken(),
				routine.getContent());
			sendMessage(request);
		}
	}

	// 루틴 알람이 존재하지 않고 fcm token이 존재하는 유저 리스트 반환
	private List<Member> getAlarmTargets() {
		List<Member> members = memberAdapter.findAllByFcmTokenIsNotNull();
		List<Long> untargetMemberIds = routineAlarmAdapter.findAll().stream().map(RoutineAlarm::getMemberId).toList();
		return members.stream()
			.filter(member -> !untargetMemberIds.contains(member.getId()))
			.toList();
	}

	private void sendMessage(final NotificationRequest request) {
		try {
			Message message = request.buildMessage().setApnsConfig(getApnsConfig(request)).build();
			firebaseMessaging.sendAsync(message);
		} catch (RuntimeException exception) {
			// TO-DO : 디스코드 에러 로그 연동
			log.error("알람 전송 실패 {토큰} = " + request.targetToken() + "\n exception = " + exception.getMessage());
		}
	}

	private ApnsConfig getApnsConfig(NotificationRequest request) {
		val alert = ApsAlert.builder().setTitle(request.title()).setBody(request.body()).build();
		val aps = Aps.builder().setAlert(alert).setSound(DEFAULT).build();
		return ApnsConfig.builder().setAps(aps).build();
	}
}
