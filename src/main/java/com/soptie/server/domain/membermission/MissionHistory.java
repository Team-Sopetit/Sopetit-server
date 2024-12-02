package com.soptie.server.domain.membermission;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MissionHistory {
	private Long id;
	private long memberMissionId;
	private long memberId;
	private long missionId;
	private LocalDateTime createdAt;
}
