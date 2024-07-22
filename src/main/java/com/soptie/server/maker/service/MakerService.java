package com.soptie.server.maker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.maker.adapter.MakerFinder;
import com.soptie.server.maker.entity.Maker;
import com.soptie.server.maker.service.dto.MakerListAcquireServiceResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MakerService {

	private final MakerFinder makerFinder;

	public MakerListAcquireServiceResponse acquireAllTheme() {
		List<Maker> makers = makerFinder.findAll();
		return MakerListAcquireServiceResponse.from(makers);
	}
}
