package com.soptie.server.persistence.repository;

import java.util.List;

import com.soptie.server.persistence.entity.Theme;

public interface ThemeCustomRepository {
	List<Theme> findAllOrderByNameAsc();

	List<Theme> findAllInBasic();
}
