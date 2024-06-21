package com.soptie.server.memberRoutine.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.memberRoutine.repository.MemberRoutineRepository;
import com.soptie.server.memberRoutine.repository.dto.MemberChallengeResponse;
import com.soptie.server.memberRoutine.repository.dto.MemberRoutineResponse;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.exception.RoutineException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.soptie.server.routine.entity.RoutineType.CHALLENGE;
import static com.soptie.server.routine.entity.RoutineType.DAILY;
import static com.soptie.server.routine.message.RoutineErrorCode.INVALID_ROUTINE;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberRoutineFinder {

	private final MemberRoutineRepository memberRoutineRepository;

	public boolean isExist(Member member, Routine routine) {
		return memberRoutineRepository.existsByMemberAndTypeAndRoutineId(member, routine.getType(), routine.getId());
	}

	public MemberRoutine findById(long id) {
		return memberRoutineRepository.findById(id)
				.orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
	}

	public List<MemberRoutine> findAchieved() {
		return memberRoutineRepository.findByIsAchieve(true);
	}

	public List<MemberRoutineResponse> findAllByMember(Member member) {
		return memberRoutineRepository.findByTypeAndMember(DAILY, member);
	}

	public boolean existMemberChallenge(Member member) {
		return memberRoutineRepository.existsByMemberAndType(member, CHALLENGE);
	}

	public Optional<MemberChallengeResponse> findChallengeByMember(Member member) {
		return memberRoutineRepository.findChallengeByMember(member);
	}
}
