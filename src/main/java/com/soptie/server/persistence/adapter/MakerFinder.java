package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.persistence.repository.MakerRepository;
import com.soptie.server.api.controller.dto.response.maker.MakerThemeResponse;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MakerFinder {

	private final MakerRepository makerRepository;

	public List<MakerThemeResponse> findAllWithTheme() {
		return makerRepository.findAllWithTheme();
	}
}
