package com.soptie.server.domain.maker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.adapter.MakerFinder;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MakerService {

	private final MakerFinder makerFinder;

	public MakerListAcquireServiceResponse acquireAll() {
		val makers = makerFinder.findAllWithTheme();
		return MakerListAcquireServiceResponse.from(makers);
	}
}
