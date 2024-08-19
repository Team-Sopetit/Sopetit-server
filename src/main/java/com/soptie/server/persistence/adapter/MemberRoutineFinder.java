package com.soptie.server.persistence.adapter;

import static com.soptie.server.common.message.RoutineErrorCode.*;
import static com.soptie.server.persistence.entity.deleted.RoutineType.*;

import java.util.List;
import java.util.Optional;

import com.soptie.server.common.exception.RoutineException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.member.Member;
import com.soptie.server.persistence.entity.deleted.MemberRoutine;
import com.soptie.server.persistence.repository.MemberRoutineRepository;
import com.soptie.server.persistence.repository.dto.MemberChallengeResponse;
import com.soptie.server.persistence.repository.dto.MemberRoutineResponse;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberRoutineFinder {

	private final MemberRoutineRepository memberRoutineRepository;

	public boolean isExist(Member member, Routine routine) {
		return memberRoutineRepository
			.existsByMemberIdAndTypeAndRoutineId(member.getId(), routine.getType(), routine.getId());
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
		return memberRoutineRepository.existsByMemberIdAndType(member.getId(), CHALLENGE);
	}

	public Optional<MemberChallengeResponse> findChallengeByMember(Member member) {
		return memberRoutineRepository.findChallengeByMember(member);
	}

	public List<MemberRoutine> findAllByMemberAndType(Member member, RoutineType type) {
		return memberRoutineRepository.findByMemberIdAndType(member.getId(), type);
	}
}
