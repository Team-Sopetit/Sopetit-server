package com.soptie.server.domain.challenge;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChallengeHistory {
	private Long id;
	private long memberMissionId;
	private long memberId;
	private long challengeId;
	private String content;
	private LocalDateTime createdAt;
}
