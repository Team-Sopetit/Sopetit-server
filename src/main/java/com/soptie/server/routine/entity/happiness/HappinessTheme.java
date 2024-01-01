package com.soptie.server.routine.entity.happiness;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HappinessTheme {
	CLOSER_TO_ME("나와 더 가까워지기💖"),
	ACHIEVEMENT("소소한 성취감🌻"),
	START_OF_DAY("하루의 시작☀️"),
	HEALTHY("건강한 몸💪"),
	SLEEP("편안함 잠🍀"),
	GRATEFUL_HEART("감사한 마음📮"),
	KINDNESS("더 친절해지기👨‍👩‍👧‍👦"),
	CONSUMPTION("잘 벌고 잘 쓰기💵"),
	ENVIRONMENT("환경🍃"),
	EMPTY("비워내기🗑️"),
	IMMERSION("몰입을 위한 준비🔥"),
	OVERCOME_HELPLESSNESS("무기력 극복☔️"),
	;

	private final String name;
}
