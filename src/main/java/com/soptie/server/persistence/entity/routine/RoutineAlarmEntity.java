package com.soptie.server.persistence.entity.routine;

import java.time.LocalTime;

import com.soptie.server.domain.memberroutine.RoutineAlarm;
import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "routine_alarm", schema = "softie")
public class RoutineAlarmEntity extends BaseEntity {

	@Column(nullable = false)
	private long memberId;

	@Column(nullable = false)
	private long memberRoutineId;

	@Column(nullable = false)
	private LocalTime alarmTime;

	public RoutineAlarmEntity(RoutineAlarm routineAlarm) {
		this.memberId = routineAlarm.getMemberId();
		this.memberRoutineId = routineAlarm.getMemberRoutineId();
		this.alarmTime = routineAlarm.getAlarmTime();
	}

	public RoutineAlarm toDomain() {
		return RoutineAlarm.builder()
			.id(id)
			.memberId(memberId)
			.memberRoutineId(memberRoutineId)
			.alarmTime(alarmTime)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();
	}
}
