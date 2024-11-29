package com.soptie.server.persistence.entity.mission;

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

	public MissionHistoryEntity(long memberMissionId, long memberId) {
		this.memberMissionId = memberMissionId;
		this.memberId = memberId;
	}
}
