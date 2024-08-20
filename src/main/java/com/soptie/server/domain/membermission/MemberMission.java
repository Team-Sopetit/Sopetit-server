package com.soptie.server.domain.membermission;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberMission {
	private Long id;
	private int achievementCount;
	private long memberId;
	private long missionId;
}
