package com.soptie.server.persistence.adapter;

import static com.soptie.server.common.message.ThemeErrorCode.*;

import java.util.List;

import com.soptie.server.common.exception.ThemeException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.persistence.entity.Theme;
import com.soptie.server.persistence.repository.ThemeRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ThemeFinder {

	private final ThemeRepository themeRepository;

	public List<Theme> findAllOrderByNameAsc() {
		return themeRepository.findAllOrderByNameAsc();
	}

	public Theme findById(long id) {
		return themeRepository.findById(id)
			.orElseThrow(() -> new ThemeException(INVALID_THEME));
	}

	public List<Theme> findAllInBasic() {
		return themeRepository.findAllInBasic();
	}

	public boolean isExistById(long id) {
		return themeRepository.existsById(id);
	}
}
