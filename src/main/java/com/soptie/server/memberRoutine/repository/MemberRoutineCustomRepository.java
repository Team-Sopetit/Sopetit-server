package com.soptie.server.memberroutine.repository;

import java.util.List;
import java.util.Optional;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberroutine.repository.dto.MemberChallengeResponse;
import com.soptie.server.memberroutine.repository.dto.MemberRoutineResponse;
import com.soptie.server.routine.entity.RoutineType;

public interface MemberRoutineCustomRepository {
	List<MemberRoutineResponse> findByTypeAndMember(RoutineType type, Member member);

	Optional<MemberChallengeResponse> findChallengeByMember(Member member);
}
