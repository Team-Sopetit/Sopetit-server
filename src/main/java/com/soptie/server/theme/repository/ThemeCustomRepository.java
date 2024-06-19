package com.soptie.server.theme.repository;

import java.util.List;

import com.soptie.server.theme.entity.Theme;

public interface ThemeCustomRepository {
	List<Theme> findAllOrderByNameAsc();
	List<Theme> findAllByNotMaker();
}
