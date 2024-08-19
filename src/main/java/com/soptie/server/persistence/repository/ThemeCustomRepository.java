package com.soptie.server.persistence.repository;

import java.util.List;

import com.soptie.server.persistence.entity.ThemeEntity;

public interface ThemeCustomRepository {
	List<ThemeEntity> findAllOrderByNameAsc();

	List<ThemeEntity> findAllInBasic();
}
