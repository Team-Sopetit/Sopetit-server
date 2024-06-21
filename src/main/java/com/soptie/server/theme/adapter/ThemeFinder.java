package com.soptie.server.theme.adapter;

import static com.soptie.server.theme.message.ThemeErrorCode.*;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.theme.entity.Theme;
import com.soptie.server.theme.exception.ThemeException;
import com.soptie.server.theme.repository.ThemeRepository;

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
}
