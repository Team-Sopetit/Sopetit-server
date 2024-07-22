package com.soptie.server.maker.adapter;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.maker.entity.Maker;
import com.soptie.server.maker.repository.MakerRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MakerFinder {

	private final MakerRepository makerRepository;

	public List<Maker> findAll() {
		return makerRepository.findAll();
	}
}
