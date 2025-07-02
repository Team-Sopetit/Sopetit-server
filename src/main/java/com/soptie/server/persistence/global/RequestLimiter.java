package com.soptie.server.persistence.global;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class RequestLimiter {

	private final Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();

	public boolean tryAcquire(String key) {
		requestCounts.putIfAbsent(key, new AtomicInteger(0));
		return requestCounts.get(key).incrementAndGet() <= 3; // 하루 최대 요청 수 제한
	}

	@Scheduled(cron = "0 0 0 * * *")  // 매일 자정 초기화
	public void resetCounts() {
		requestCounts.clear();
	}
}
