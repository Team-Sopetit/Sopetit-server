package com.soptie.server.persistence.adapter;

import static com.soptie.server.common.message.RoutineErrorCode.INVALID_ROUTINE;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.common.exception.RoutineException;
import com.soptie.server.persistence.repository.ChallengeRepository;
import com.soptie.server.domain.routine.ChallengeVO;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ChallengeFinder {

	private final ChallengeRepository challengeRepository;

	public Challenge findById(long id) {
		return challengeRepository.findById(id)
			.orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
	}

	public List<ChallengeVO> findByRoutine(Routine routine) {
		return challengeRepository.findByRoutine(routine).stream().map(ChallengeVO::from).toList();
	}
}
