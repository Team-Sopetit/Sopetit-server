package com.soptie.server.domain.theme;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.adapter.ThemeAdapter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThemeService {

	private final ThemeAdapter themeAdapter;

	public List<Theme> getAll() {
		return themeAdapter.findAll()
			.stream()
			.sorted(Comparator.comparing(Theme::getSequence))
			.toList();
	}
}
