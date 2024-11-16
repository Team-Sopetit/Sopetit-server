package com.soptie.server.persistence.entity.routine;

import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "routine_history", schema = "softie")
public class RoutineHistoryEntity extends BaseEntity {
	private long memberRoutineId;

	public RoutineHistoryEntity(long memberRoutineId) {
		this.memberRoutineId = memberRoutineId;
	}
}
