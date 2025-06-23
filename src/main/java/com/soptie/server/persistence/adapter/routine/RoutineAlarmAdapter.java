package com.soptie.server.persistence.adapter.routine;

import java.time.LocalTime;
import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.memberroutine.RoutineAlarm;
import com.soptie.server.persistence.entity.routine.RoutineAlarmEntity;
import com.soptie.server.persistence.repository.routine.RoutineAlarmRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class RoutineAlarmAdapter {

	private final RoutineAlarmRepository routineAlarmRepository;

	public List<RoutineAlarm> findAll() {
		return routineAlarmRepository.findAll()
			.stream()
			.map(RoutineAlarmEntity::toDomain)
			.toList();
	}

	public List<RoutineAlarm> findAllByAlarmTime(LocalTime alarmTime) {
		return routineAlarmRepository.findAllByAlarmTime(alarmTime)
			.stream()
			.map(RoutineAlarmEntity::toDomain)
			.toList();
	}

	public void save(RoutineAlarm routineAlarm) {
		routineAlarmRepository.save(new RoutineAlarmEntity(routineAlarm));
	}
}
