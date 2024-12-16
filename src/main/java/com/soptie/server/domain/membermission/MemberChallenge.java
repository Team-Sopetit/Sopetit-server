package com.soptie.server.domain.membermission;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberChallenge {
	private Long id;
	private int achievedCount;
	private boolean achieved;
	private long challengeId;
	private long memberId;

	public void achieve() {
		this.achievedCount++;
		this.achieved = true;
	}
}
