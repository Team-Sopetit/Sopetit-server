package com.soptie.server.domain.doll;

import static com.soptie.server.common.message.DollErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.response.doll.DollImageResponse;
import com.soptie.server.domain.usecase.DollService;
import com.soptie.server.persistence.repository.DollRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DollServiceImpl implements DollService {

	private final DollRepository dollRepository;

	@Override
	public DollImageResponse getDollImage(DollType type) {
		val doll = findDoll(type);
		return DollImageResponse.of(doll);
	}

	private Doll findDoll(DollType type) {
		return dollRepository.findByDollType(type)
			.orElseThrow(() -> new DollException(INVALID_TYPE));
	}
}
