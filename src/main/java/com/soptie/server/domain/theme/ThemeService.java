package com.soptie.server.domain.theme;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.response.theme.GetThemesResponse;
import com.soptie.server.persistence.adapter.ThemeAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThemeService {
	private final ThemeAdapter themeAdapter;

	public GetThemesResponse getBasicThemes() {
		val themes = themeAdapter.findByBasic();
		return GetThemesResponse.of(themes);
	}
}
