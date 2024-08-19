package com.soptie.server.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.soptie.server.domain.member.Member;
import com.soptie.server.persistence.repository.dto.MemberChallengeResponse;
import com.soptie.server.persistence.repository.dto.MemberRoutineResponse;

public interface MemberRoutineCustomRepository {
	List<MemberRoutineResponse> findByTypeAndMember(RoutineType type, Member member);

	Optional<MemberChallengeResponse> findChallengeByMember(Member member);
}
