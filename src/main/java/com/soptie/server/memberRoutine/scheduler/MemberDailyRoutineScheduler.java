package com.soptie.server.memberRoutine.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.soptie.server.memberRoutine.service.MemberRoutineService;

import lombok.RequiredArgsConstructor;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MemberDailyRoutineScheduler {

	private final MemberRoutineService memberRoutineService;

	@Scheduled(cron = "${softie.cron.init.routine}")
	public void initMemberDailyRoutines() {
		memberRoutineService.initDailyRoutines();
	}
}
