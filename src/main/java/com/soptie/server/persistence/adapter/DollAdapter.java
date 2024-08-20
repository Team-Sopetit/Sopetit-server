package com.soptie.server.persistence.adapter;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.doll.Doll;
import com.soptie.server.domain.doll.DollType;
import com.soptie.server.persistence.repository.DollRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class DollAdapter {
	private final DollRepository dollRepository;

	public Doll findByType(DollType type) {
		return dollRepository.findByType(type)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Doll Type: " + type))
			.toDomain();
	}
}
