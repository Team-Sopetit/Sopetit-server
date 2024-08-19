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
}
