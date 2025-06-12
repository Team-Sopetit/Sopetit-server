package com.soptie.server.domain.memberroutine;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class RoutineAlarm {
	private Long id;
	private long memberId;
	private long memberRoutineId;
	private LocalTime alarmTime;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public boolean isAlarmTime(LocalTime alarmTime) {
		return this.alarmTime.equals(alarmTime);
	}
}
