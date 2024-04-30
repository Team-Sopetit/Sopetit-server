package com.soptie.server.support.fixture;

import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.RoutineType;
import com.soptie.server.theme.entity.Theme;

public class RoutineFixture {

	private Long id;
	private String content;
	private RoutineType type;
	private Theme theme;

	private RoutineFixture() {
	}

	public static RoutineFixture routine() {
		return new RoutineFixture();
	}

	public RoutineFixture id(Long id) {
		this.id = id;
		return this;
	}

	public RoutineFixture content(String content) {
		this.content = content;
		return this;
	}

	public RoutineFixture type(RoutineType type) {
		this.type = type;
		return this;
	}

	public RoutineFixture theme(Theme theme) {
		this.theme = theme;
		return this;
	}

	public Routine build() {
		return new Routine(id, content, type, theme);
	}
}
