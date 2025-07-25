package com.soptie.server.batch;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.soptie.server.domain.memberroutine.MemberRoutineService;

import lombok.RequiredArgsConstructor;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MemberRoutineScheduler {
	private final MemberRoutineService memberRoutineService;

	@Scheduled(cron = "0 0 0 * * *")
	public void initMemberDailyRoutines() {
		memberRoutineService.initAchievement();
	}
}
