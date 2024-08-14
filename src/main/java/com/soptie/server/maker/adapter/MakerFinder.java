package com.soptie.server.maker.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.maker.repository.MakerRepository;
import com.soptie.server.maker.repository.dto.MakerThemeResponse;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MakerFinder {

	private final MakerRepository makerRepository;

	public List<MakerThemeResponse> findAllWithTheme() {
		return makerRepository.findAllWithTheme();
	}
}
