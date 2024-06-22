package com.soptie.server.memberroutine.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.soptie.server.memberroutine.service.MemberRoutineUpdateService;

import lombok.RequiredArgsConstructor;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MemberDailyRoutineScheduler {

	private final MemberRoutineUpdateService memberRoutineUpdateService;

	@Scheduled(cron = "${softie.cron.init.routine}")
	public void initMemberDailyRoutines() {
		memberRoutineUpdateService.initDailyRoutines();
	}
}
