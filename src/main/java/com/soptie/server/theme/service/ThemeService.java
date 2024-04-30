package com.soptie.server.theme.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.theme.adapter.ThemeFinder;
import com.soptie.server.theme.service.dto.response.ThemeListGetServiceResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThemeService {

	private final ThemeFinder themeFinder;

	public ThemeListGetServiceResponse getThemes() {
		val themes = themeFinder.findAllOrderByNameAsc();
		return ThemeListGetServiceResponse.of(themes);
	}
}
