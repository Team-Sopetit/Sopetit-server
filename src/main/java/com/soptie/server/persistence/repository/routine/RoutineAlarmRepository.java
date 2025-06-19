package com.soptie.server.persistence.repository.routine;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.routine.RoutineAlarmEntity;

public interface RoutineAlarmRepository extends JpaRepository<RoutineAlarmEntity, Long> {

	List<RoutineAlarmEntity> findAllByAlarmTime(LocalTime alarmTime);
}
