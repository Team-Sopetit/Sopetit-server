package com.soptie.server.batch;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;

import lombok.RequiredArgsConstructor;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class MemberRoutineScheduler {
	private final MemberRoutineAdapter memberRoutineAdapter;

	//todo. 재시도 처리
	@Scheduled(cron = "0 0 0 * * *")
	@Transactional
	public void initMemberDailyRoutines() {
		memberRoutineAdapter.initAllAchievement();
	}
}
