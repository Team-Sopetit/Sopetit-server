package com.soptie.server.persistence.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.soptie.server.domain.membermission.MemberMission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE softie.member_mission SET is_deleted = true WHERE id = ?")
@Table(name = "member_mission", schema = "softie")
public class MemberMissionEntity extends BaseEntity {
	@Column(nullable = false)
	private int achievementCount;
	@Column(nullable = false)
	private boolean isDeleted;
	@Column(nullable = false)
	private long memberId;
	@Column(nullable = false)
	private long missionId;

	public MemberMission toDomain() {
		return MemberMission.builder()
			.id(this.id)
			.achievementCount(this.achievementCount)
			.memberId(this.memberId)
			.missionId(this.missionId)
			.build();
	}
}
