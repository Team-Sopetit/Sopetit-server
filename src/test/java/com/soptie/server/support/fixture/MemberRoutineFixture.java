package com.soptie.server.support.fixture;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.routine.entity.RoutineType;

public class MemberRoutineFixture {

	private Long id;
	private final boolean isAchieve = false;
	private final int achieveCount = 0;
	private RoutineType type;
	private long routineId;
	private Member member;

	private MemberRoutineFixture() {
	}

	public static MemberRoutineFixture memberRoutine() {
		return new MemberRoutineFixture();
	}

	public MemberRoutineFixture id(Long id) {
		this.id = id;
		return this;
	}

	public MemberRoutineFixture type(RoutineType type) {
		this.type = type;
		return this;
	}

	public MemberRoutineFixture routineId(long routineId) {
		this.routineId = routineId;
		return this;
	}

	public MemberRoutineFixture member(Member member) {
		this.member = member;
		return this;
	}

	public MemberRoutine build() {
		return new MemberRoutine(id, isAchieve, achieveCount, type, routineId, member);
	}
}
