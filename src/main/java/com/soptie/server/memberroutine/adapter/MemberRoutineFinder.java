package com.soptie.server.memberroutine.adapter;

import static com.soptie.server.routine.entity.RoutineType.*;
import static com.soptie.server.routine.message.RoutineErrorCode.*;

import java.util.List;
import java.util.Optional;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberroutine.entity.MemberRoutine;
import com.soptie.server.memberroutine.repository.MemberRoutineRepository;
import com.soptie.server.memberroutine.repository.dto.MemberChallengeResponse;
import com.soptie.server.memberroutine.repository.dto.MemberRoutineResponse;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.RoutineType;
import com.soptie.server.routine.exception.RoutineException;

import lombok.RequiredArgsConstructor;

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

	public List<MemberRoutine> findAllByMemberAndType(Member member, RoutineType type) {
		return memberRoutineRepository.findByMemberAndType(member, type);
	}
}
