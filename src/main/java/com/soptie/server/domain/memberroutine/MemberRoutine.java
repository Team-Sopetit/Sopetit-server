package com.soptie.server.domain.memberroutine;

import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.routine.Routine;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRoutine {
	private Long id;
	private boolean isAchieved;
	private boolean isAchievedToday;
	private int achievementCount;
	private long memberId;
	private long routineId;

	public MemberRoutine(Member member, Routine routine) {
		this.isAchieved = false;
		this.isAchievedToday = false;
		this.achievementCount = 0;
		this.memberId = member.getId();
		this.routineId = routine.getId();
	}

	public void achieve() {
		this.isAchievedToday = true;
		this.achievementCount += !this.isAchieved ? 1 : -1;
		this.isAchieved = !this.isAchieved;
	}
}
