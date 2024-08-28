package com.soptie.server.persistence.repository;

import java.util.List;

import com.soptie.server.api.controller.dto.response.maker.MakerThemeResponse;

public interface ThemeCustomRepository {
	List<MakerThemeResponse> findAllWithMaker();
}
