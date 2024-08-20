package com.soptie.server.domain.memberroutine;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberRoutine {
	private Long id;
	private boolean isAchieved;
	private boolean isAchievedToday;
	private int achievementCount;
	private long memberId;
	private long routineId;

	public void achieve() {
		this.isAchievedToday = true;
		this.achievementCount += this.isAchieved ? 1 : -1;
		this.isAchieved = !this.isAchieved;
	}

	public void initAchievement() {
		this.isAchievedToday = false;
		this.isAchieved = false;
	}
}
