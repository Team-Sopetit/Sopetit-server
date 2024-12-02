package com.soptie.server.persistence.entity.routine;

import com.soptie.server.domain.memberroutine.RoutineHistory;
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
	private long memberId;
	private long routineId;

	public RoutineHistoryEntity(long memberRoutineId, long memberId, long routineId) {
		this.memberRoutineId = memberRoutineId;
		this.memberId = memberId;
		this.routineId = routineId;
	}

	public RoutineHistory toDomain() {
		return RoutineHistory.builder()
			.id(this.id)
			.memberRoutineId(this.memberRoutineId)
			.memberId(this.memberId)
			.routineId(this.routineId)
			.createdAt(this.createdAt)
			.build();
	}
}
