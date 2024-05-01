package com.soptie.server.memberRoutine.repository;

import java.util.List;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.repository.dto.MemberRoutineResponse;
import com.soptie.server.routine.entity.RoutineType;

public interface MemberRoutineCustomRepository {
	List<MemberRoutineResponse> findByTypeAndMember(RoutineType type, Member member);
}
