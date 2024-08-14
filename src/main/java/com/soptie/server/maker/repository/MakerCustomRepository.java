package com.soptie.server.maker.repository;

import java.util.List;

import com.soptie.server.maker.repository.dto.MakerThemeResponse;

public interface MakerCustomRepository {
	List<MakerThemeResponse> findAllWithTheme();
}
