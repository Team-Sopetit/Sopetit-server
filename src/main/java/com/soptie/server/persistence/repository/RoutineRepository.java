package com.soptie.server.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soptie.server.persistence.entity.RoutineEntity;

public interface RoutineRepository extends JpaRepository<RoutineEntity, Long>, RoutineCustomRepository {
	//TODO: 데이터베이스 내부 자체 처리 방법 찾아보기
	@Query(
		value = "SELECT * FROM routine_entity WHERE theme_id = :themeId ORDER BY content COLLATE \"ko_KR.utf8\" ASC",
		nativeQuery = true)
	List<RoutineEntity> findByThemeIdOrderByContentAsc(long themeId);

	List<RoutineEntity> findByIdIn(List<Long> ids);
}