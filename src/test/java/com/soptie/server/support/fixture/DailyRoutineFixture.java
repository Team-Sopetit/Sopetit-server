package com.soptie.server.support.fixture;

import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;

public class DailyRoutineFixture {

	private Long id;
	private String content;
	private DailyTheme theme = DailyThemeFixture.dailyTheme().build();

	private DailyRoutineFixture() {
	}

	public static DailyRoutineFixture dailyRoutine() {
		return new DailyRoutineFixture();
	}

	public DailyRoutineFixture id(Long id) {
		this.id = id;
		return this;
	}

	public DailyRoutineFixture content(String content) {
		this.content = content;
		return this;
	}

	public DailyRoutineFixture theme(DailyTheme theme) {
		this.theme = theme;
		return this;
	}

	public DailyRoutine build() {
		return new DailyRoutine(id, content, theme);
	}
}
