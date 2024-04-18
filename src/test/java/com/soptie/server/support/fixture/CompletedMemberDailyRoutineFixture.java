package com.soptie.server.support.fixture;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.daily.CompletedMemberDailyRoutine;
import com.soptie.server.routine.entity.daily.DailyRoutine;

public class CompletedMemberDailyRoutineFixture {

	private Long id;
	private Member member;
	private DailyRoutine routine;
	private int achieveCount = 0;

	private CompletedMemberDailyRoutineFixture() {
	}

	public static CompletedMemberDailyRoutineFixture completedMemberDailyRoutine() {
		return new CompletedMemberDailyRoutineFixture();
	}

	public CompletedMemberDailyRoutineFixture id(Long id) {
		this.id = id;
		return this;
	}

	public CompletedMemberDailyRoutineFixture member(Member member) {
		this.member = member;
		return this;
	}

	public CompletedMemberDailyRoutineFixture routine(DailyRoutine routine) {
		this.routine = routine;
		return this;
	}

	public CompletedMemberDailyRoutine build() {
		return new CompletedMemberDailyRoutine(id, member, routine, achieveCount);
	}
}
