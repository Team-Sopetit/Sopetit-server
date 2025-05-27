package com.soptie.server.domain.theme;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.global.ThemeStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThemeService {

	private final ThemeStore themeStore;

	public List<Theme> getAll() {
		return themeStore.getAll()
			.stream()
			.sorted(Comparator.comparing(Theme::getSequence))
			.toList();
	}
}
