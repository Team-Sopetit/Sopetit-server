package com.soptie.server.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.ThemeEntity;

public interface ThemeRepository extends JpaRepository<ThemeEntity, Long>, ThemeCustomRepository {
	List<ThemeEntity> findByMakerIdIsNullOrderBySequenceAsc();

	List<ThemeEntity> findByIdIn(List<Long> ids);
}
