package com.soptie.server.persistence.adapter.routine;

import java.time.LocalTime;
import java.util.List;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
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

	public RoutineAlarm findByMemberRoutineAlarmId(long memberRoutineId) {
		return routineAlarmRepository.findByMemberRoutineId(memberRoutineId).toDomain();
	}

	public void update(RoutineAlarm routineAlarm) {
		RoutineAlarmEntity routineAlarmEntity = find(routineAlarm.getId());
		routineAlarmEntity.update(routineAlarm);
	}

	public void save(RoutineAlarm routineAlarm) {
		routineAlarmRepository.save(new RoutineAlarmEntity(routineAlarm));
	}

	public void deleteByMemberRoutineId(long memberRoutineId) {
		routineAlarmRepository.deleteByMemberRoutineId(memberRoutineId);
	}

	private RoutineAlarmEntity find(long id) {
		return routineAlarmRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "RoutineAlarm ID: " + id));
	}
}
