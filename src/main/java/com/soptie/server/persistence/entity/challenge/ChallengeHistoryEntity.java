package com.soptie.server.persistence.entity.challenge;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.membermission.MemberChallenge;
import com.soptie.server.domain.membermission.MissionHistory;
import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "challenge_history", schema = "softie")
public class ChallengeHistoryEntity extends BaseEntity {
	private long memberChallengeId;
	private long memberId;
	private long missionId;
	private String content;

	public ChallengeHistoryEntity(final MemberChallenge memberChallenge, final Challenge challenge) {
		this.memberChallengeId = memberChallenge.getId();
		this.memberId = memberChallenge.getMemberId();
		this.missionId = challenge.getId();
		this.content = challenge.getContent();
	}

	public MissionHistory toDomain() {
		return MissionHistory.builder()
			.id(this.id)
			.memberMissionId(this.memberChallengeId)
			.memberId(this.memberId)
			.missionId(this.missionId)
			.content(this.content)
			.createdAt(this.createdAt)
			.build();
	}
}
