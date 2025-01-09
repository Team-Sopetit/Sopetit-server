package com.soptie.server.persistence.entity.challenge;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.MemberChallenge;
import com.soptie.server.domain.member.Member;
import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
	name = "member_challenge",
	schema = "softie",
	uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "challenge_id"}))
public class MemberChallengeEntity extends BaseEntity {
	@Column(nullable = false)
	private boolean achieved;
	@Column(nullable = false)
	private int achievedCount;
	@Column(nullable = false)
	private long challengeId;
	@Column(nullable = false)
	private long memberId;

	public MemberChallengeEntity(Member member, Challenge challenge) {
		this.achieved = false;
		this.achievedCount = 0;
		this.challengeId = challenge.getId();
		this.memberId = member.getId();
	}

	public MemberChallenge toDomain() {
		return MemberChallenge.builder()
			.id(this.id)
			.achievedCount(this.achievedCount)
			.challengeId(this.challengeId)
			.memberId(this.memberId)
			.createdAt(this.createdAt.toLocalDate())
			.build();
	}

	public MemberChallengeEntity restore() {
		this.achieved = false;
		return this;
	}

	public void delete() {
		this.achieved = true;
	}

	public void update(MemberChallenge memberChallenge) {
		this.achievedCount = memberChallenge.getAchievedCount();
		this.achieved = memberChallenge.isAchieved();
	}
}
