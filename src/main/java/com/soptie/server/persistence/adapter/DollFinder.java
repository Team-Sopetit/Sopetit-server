package com.soptie.server.persistence.adapter;

import static com.soptie.server.common.message.DollErrorCode.*;

import com.soptie.server.common.exception.DollException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.doll.Doll;
import com.soptie.server.domain.doll.DollType;
import com.soptie.server.persistence.repository.DollRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DollFinder {
	private final DollRepository dollRepository;

	public Doll findByType(DollType type) {
		return dollRepository.findByType(type)
			.orElseThrow(() -> new DollException(INVALID_TYPE))
			.toDomain();
	}
}
