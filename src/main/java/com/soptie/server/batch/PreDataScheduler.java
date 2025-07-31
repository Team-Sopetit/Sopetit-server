package com.soptie.server.batch;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.global.ChallengeStore;
import com.soptie.server.persistence.global.RoutineStore;
import com.soptie.server.persistence.global.ThemeStore;

import lombok.RequiredArgsConstructor;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PreDataScheduler {
	private ThemeStore themeStore;
	private RoutineStore routineStore;
	private ChallengeStore challengeStore;

	@Scheduled(cron = "0 0 3 * * *")
	public void initStore() {
		themeStore.init();
		routineStore.init();
		challengeStore.init();
	}
}
