package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.entity.ThemeEntity;
import com.soptie.server.persistence.repository.ThemeRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ThemeAdapter {

	private final ThemeRepository themeRepository;

	public List<Theme> findByBasic() {
		return themeRepository.findByOrderBySequenceAsc()
			.stream().map(ThemeEntity::toDomain)
			.toList();
	}

	public Theme findById(long id) {
		return find(id).toDomain();
	}

	public List<Theme> findByIds(List<Long> ids) {
		return themeRepository.findByIdIn(ids)
			.stream().map(ThemeEntity::toDomain)
			.toList();
	}

	public boolean isExistById(long id) {
		return themeRepository.existsById(id);
	}

	private ThemeEntity find(long id) {
		return themeRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Theme ID: " + id));
	}
}
