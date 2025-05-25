package com.soptie.server.persistence.global;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mysema.commons.lang.Pair;
import com.soptie.server.config.support.GlobalData;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.entity.ThemeEntity;
import com.soptie.server.persistence.repository.ThemeRepository;

import io.jsonwebtoken.lang.Collections;
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

	@Scheduled(cron = "0 0 0 * * *")
	public void init() {
		try {
			update();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			themes = new HashMap<>();
		}
	}

	public Theme get(long id) {
		if (Collections.isEmpty(themes)) {
			update();
		}

		if (!themes.containsKey(id)) {
			return null;
		}

		return themes.get(id);
	}

	private void update() {
		themes = themeRepository.findAll()
			.stream()
			.map(ThemeEntity::toDomain)
			.map(theme -> Pair.of(theme.getId(), theme))
			.filter(routine -> routine.getFirst() != null && routine.getSecond() != null)
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
	}
}
