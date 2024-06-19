package com.soptie.server.theme.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.theme.adapter.ThemeFinder;
import com.soptie.server.theme.service.dto.response.ThemeListServiceResponse;
import com.soptie.server.theme.service.dto.response.ThemeVO;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThemeService {

	private final ThemeFinder themeFinder;

	public ThemeListServiceResponse getThemes() {
		val themes = themeFinder.findAllOrderByNameAsc();
		return ThemeListServiceResponse.of(themes);
	}

	public List<ThemeVO> acquireAllByNotMaker() {
		val themes = themeFinder.findAllByNotMaker();
		return themes.stream().map(ThemeVO::from).toList();
	}
}
