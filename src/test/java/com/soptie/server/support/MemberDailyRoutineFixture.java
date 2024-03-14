package com.soptie.server.support;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.routine.entity.daily.DailyRoutine;

public class MemberDailyRoutineFixture {

	private Long id;
	private Member member = MemberFixture.member().build();
	private DailyRoutine routine = DailyRoutineFixture.dailyRoutine().build();

	private MemberDailyRoutineFixture() {
	}

	public static MemberDailyRoutineFixture memberRoutine() {
		return new MemberDailyRoutineFixture();
	}

	public MemberDailyRoutineFixture id(Long id) {
		this.id = id;
		return this;
	}

	public MemberDailyRoutineFixture member(Member member) {
		this.member = member;
		return this;
	}

	public MemberDailyRoutineFixture routine(DailyRoutine routine) {
		this.routine = routine;
		return this;
	}

	public MemberDailyRoutine build() {
		return new MemberDailyRoutine(id, member, routine);
	}
}
