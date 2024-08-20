package com.soptie.server.domain.maker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.adapter.MakerAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MakerService {

	private final MakerAdapter makerAdapter;

	public MakerListAcquireServiceResponse acquireAll() {
		val makers = makerAdapter.findAllWithTheme();
		return MakerListAcquireServiceResponse.from(makers);
	}
}
