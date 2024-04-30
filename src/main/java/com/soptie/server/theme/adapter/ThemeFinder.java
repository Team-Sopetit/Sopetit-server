package com.soptie.server.theme.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.theme.entity.Theme;
import com.soptie.server.theme.repository.ThemeRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ThemeFinder {

	private final ThemeRepository themeRepository;

	public List<Theme> findAllOrderByNameAsc() {
		return themeRepository.findAllOrderByNameAsc();
	}
}
