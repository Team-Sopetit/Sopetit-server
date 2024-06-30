package com.soptie.server.theme.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.theme.adapter.ThemeFinder;
import com.soptie.server.theme.service.vo.ThemeVO;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThemeService {

	private final ThemeFinder themeFinder;

	public List<ThemeVO> acquireAllInBasic() {
		val themes = themeFinder.findAllInBasic();
		return themes.stream().map(ThemeVO::from).toList();
	}

	public ThemeVO acquireById(long themeId) {
		return ThemeVO.from(themeFinder.findById(themeId));
	}
}
