package com.soptie.server.support.fixture;

import com.soptie.server.persistence.entity.Routine;
import com.soptie.server.persistence.entity.RoutineType;
import com.soptie.server.persistence.entity.Theme;

public class RoutineFixture {

	private Long id;
	private String content = "널 방지용 내용 넣기";
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
