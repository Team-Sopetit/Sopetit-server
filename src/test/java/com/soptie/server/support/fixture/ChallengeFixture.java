package com.soptie.server.support.fixture;

import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.Challenge;

public class ChallengeFixture {

	private Long id;
	private String content = "널 방지용 내용 넣기";
	private String description;
	private String requiredTime;
	private String place;
	private Routine routine;

	private ChallengeFixture() {
	}

	public static ChallengeFixture challenge() {
		return new ChallengeFixture();
	}

	public ChallengeFixture id(Long id) {
		this.id = id;
		return this;
	}

	public ChallengeFixture content(String content) {
		this.content = content;
		return this;
	}

	public ChallengeFixture description(String description) {
		this.description = description;
		return this;
	}

	public ChallengeFixture requiredTime(String requiredTime) {
		this.requiredTime = requiredTime;
		return this;
	}

	public ChallengeFixture place(String place) {
		this.place = place;
		return this;
	}

	public ChallengeFixture routine(Routine routine) {
		this.routine = routine;
		return this;
	}

	public Challenge build() {
		return new Challenge(id, content, description, requiredTime, place, routine);
	}
}
