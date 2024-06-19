package com.soptie.server.theme.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.theme.adapter.ThemeFinder;
import com.soptie.server.theme.service.dto.response.ThemeListSearchServiceResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThemeService {

	private final ThemeFinder themeFinder;

	public ThemeListSearchServiceResponse getThemes() {
		val themes = themeFinder.findAllOrderByNameAsc();
		return ThemeListSearchServiceResponse.of(themes);
	}
}
