package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.entity.RoutineEntity;
import com.soptie.server.persistence.repository.RoutineRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class RoutineAdapter {
	private final RoutineRepository routineRepository;

	public List<Routine> findByIds(List<Long> ids) {
		return routineRepository.findByIdIn(ids).stream().map(RoutineEntity::toDomain).toList();
	}

	public List<Routine> findByThemeId(long themeId) {
		return routineRepository.findByThemeIdOrderByContentAsc(themeId).stream()
			.map(RoutineEntity::toDomain)
			.toList();
	}

	public Routine findById(long id) {
		return find(id).toDomain();
	}

	private RoutineEntity find(long id) {
		return routineRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Routine ID: " + id));
	}
}
