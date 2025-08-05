package com.soptie.server.persistence.global;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mysema.commons.lang.Pair;
import com.soptie.server.common.utils.MapUtils;
import com.soptie.server.config.support.GlobalData;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.entity.ThemeEntity;
import com.soptie.server.persistence.repository.ThemeRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThemeStore {

	private final ThemeRepository themeRepository;

	@Getter
	@GlobalData
	private Map<Long, Theme> themes;

	private LocalDate updateDate;

	public void init() {
		if (MapUtils.isNotEmpty(this.themes) && LocalDate.now().equals(this.updateDate)) {
			return;
		}

		try {
			this.themes = getNewThemes();
			this.updateDate = LocalDate.now();
			log.info("[ThemeStore] successfully updated. count={}", themes.size());
		} catch (Exception e) {
			this.themes = new HashMap<>();
			this.updateDate = null;
			log.error("[ThemeStore] not updated.", e);
		}
	}

	public Theme get(long id) {
		if (MapUtils.isEmpty(this.themes)) {
			return getFallback(id);
		}

		return themes.getOrDefault(id, null);
	}

	public List<Theme> getAll() {
		return themes.values().stream().toList();
	}

	public Long getValidatedId(long id) {
		return Optional.ofNullable(get(id)).map(Theme::getId).orElse(null);
	}

	private Map<Long, Theme> getNewThemes() {
		return themeRepository.findAll()
			.stream()
			.map(ThemeEntity::toDomain)
			.map(theme -> Pair.of(theme.getId(), theme))
			.filter(routine -> routine.getFirst() != null && routine.getSecond() != null)
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
	}

	private Theme getFallback(long id) {
		return themeRepository.findById(id)
			.map(ThemeEntity::toDomain)
			.orElse(null);
	}
}
