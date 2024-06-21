package com.soptie.server.member.repository;

import java.util.List;
import java.util.Optional;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.dto.MemberChallengeResponse;
import com.soptie.server.member.repository.dto.MemberRoutineResponse;
import com.soptie.server.routine.entity.RoutineType;

public interface MemberRoutineCustomRepository {
	List<MemberRoutineResponse> findByTypeAndMember(RoutineType type, Member member);

	Optional<MemberChallengeResponse> findChallengeByMember(Member member);
}
