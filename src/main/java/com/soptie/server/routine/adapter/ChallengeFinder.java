package com.soptie.server.routine.adapter;

import static com.soptie.server.routine.message.RoutineErrorCode.*;

import java.util.List;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.routine.entity.Challenge;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class ChallengeFinder {

	private final ChallengeRepository challengeRepository;

	public Challenge findById(long id) {
		return challengeRepository.findById(id)
			.orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
	}

	public List<Challenge> findByRoutine(Routine routine) {
		return challengeRepository.findByRoutine(routine);
	}
}
