package com.soptie.server.support.fixture;

import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;

public class MemberRoutineFixture {

	public static MemberRoutine createDefault(Member member, Routine routine) {
		return new MemberRoutine(member, routine);
	}
}
