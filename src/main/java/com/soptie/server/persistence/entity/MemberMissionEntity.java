package com.soptie.server.persistence.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.soptie.server.domain.challenge.Mission;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.membermission.MemberMission;

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
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE softie.member_mission SET is_deleted = true WHERE id = ?")
@Table(
	name = "member_mission",
	schema = "softie",
	uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "mission_id"}))
public class MemberMissionEntity extends BaseEntity {
	@Column(nullable = false)
	private int achievementCount;
	@Column(nullable = false)
	private boolean isDeleted;
	@Column(nullable = false)
	private long memberId;
	@Column(nullable = false)
	private long missionId;

	public MemberMissionEntity(Member member, Mission mission) {
		this.achievementCount = 0;
		this.memberId = member.getId();
		this.missionId = mission.getId();
	}

	public MemberMission toDomain() {
		return MemberMission.builder()
			.id(this.id)
			.achievementCount(this.achievementCount)
			.memberId(this.memberId)
			.missionId(this.missionId)
			.build();
	}

	public void update(MemberMission memberMission) {
		this.achievementCount += memberMission.getAchievementCount();
	}

	public void restore() {
		this.isDeleted = false;
	}
}
