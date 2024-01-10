package com.soptie.server.doll.service;

import static com.soptie.server.doll.message.ErrorMessage.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.doll.dto.DollImageResponse;
import com.soptie.server.doll.entity.Doll;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.repository.DollRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DollServiceImpl implements DollService {

	private final DollRepository dollRepository;

	@Override
	public DollImageResponse getDollImage(DollType type) {
		val doll = getDoll(type);
		return DollImageResponse.of(doll);
	}

	private Doll getDoll(DollType type) {
		return dollRepository.findByDollType(type)
			.orElseThrow(() -> new EntityNotFoundException(INVALID_TYPE.getMessage()));
	}
}