package com.soptie.server.support.fixture;

import com.soptie.server.domain.routine.Routine;

public class RoutineFixture {
	String content = "테스트용 루틴";
	long themeId = 0L;

	public static RoutineFixture create() {
		return new RoutineFixture();
	}

	public RoutineFixture content(String content) {
		this.content = content;
		return this;
	}

	public RoutineFixture themeId(Long themeId) {
		this.themeId = themeId;
		return this;
	}

	public Routine build() {
		return new Routine(content, themeId);
	}
}
