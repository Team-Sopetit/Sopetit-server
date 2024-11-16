package com.soptie.server.persistence.entity.mission;

import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mission_history", schema = "softie")
public class MissionHistoryEntity extends BaseEntity {
	private long memberMissionId;
}
