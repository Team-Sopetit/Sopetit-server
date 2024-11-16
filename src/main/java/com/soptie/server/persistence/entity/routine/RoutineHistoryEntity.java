package com.soptie.server.persistence.entity.routine;

import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "routine_history", schema = "softie")
public class RoutineHistoryEntity extends BaseEntity {
	private long memberRoutineId;
}
