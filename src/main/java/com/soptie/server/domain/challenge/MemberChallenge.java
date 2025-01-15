package com.soptie.server.domain.challenge;

import java.time.LocalDate;

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
	private LocalDate createdAt;

	public void achieve() {
		this.achievedCount++;
		this.achieved = true;
	}

	public void cancel() {
		this.achievedCount--;
	}
}
