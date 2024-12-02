package com.soptie.server.persistence.entity.mission;

import com.soptie.server.domain.membermission.MissionHistory;
import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mission_history", schema = "softie")
public class MissionHistoryEntity extends BaseEntity {
	private long memberMissionId;
	private long memberId;
	private long missionId;
	private String content;

	public MissionHistoryEntity(long memberMissionId, long memberId, long missionId, String content) {
		this.memberMissionId = memberMissionId;
		this.memberId = memberId;
		this.missionId = missionId;
		this.content = content;
	}

	public MissionHistory toDomain() {
		return MissionHistory.builder()
			.id(this.id)
			.id(this.memberMissionId)
			.memberId(this.memberId)
			.missionId(this.missionId)
			.content(this.content)
			.createdAt(this.createdAt)
			.build();
	}
}
