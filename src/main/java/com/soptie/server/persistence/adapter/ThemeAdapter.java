package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.common.utils.CollectionUtils;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.entity.ThemeEntity;
import com.soptie.server.persistence.global.ThemeStore;
import com.soptie.server.persistence.repository.ThemeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RepositoryAdapter
@RequiredArgsConstructor
public class ThemeAdapter {

	private final ThemeRepository themeRepository;
	private final ThemeStore themeStore;

	public Theme findById(long id) {
		Theme theme = themeStore.get(id);
		return theme != null ? theme : findByIdFallback(id);
	}

	private Theme findByIdFallback(long themeId) {
		return find(themeId).toDomain();
	}

	public List<Theme> findByIds(List<Long> ids) {
		return themeRepository.findByIdIn(ids)
			.stream().map(ThemeEntity::toDomain)
			.toList();
	}

	public List<Theme> findAll() {
		try {
			List<Theme> themes = themeStore.getAll();
			return CollectionUtils.isNotEmpty(themes) ? themes : findAllFallback();
		} catch (Exception e) {
			log.error("[ThemeAdapter] failed to find themes", e);
			return findAllFallback();
		}
	}

	private List<Theme> findAllFallback() {
		return themeRepository.findAll()
			.stream()
			.map(ThemeEntity::toDomain)
			.toList();
	}

	private ThemeEntity find(long id) {
		return themeRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Theme ID: " + id));
	}
}
