package com.soptie.server.memberRoutine.adapter;

import static com.soptie.server.routine.message.RoutineErrorCode.*;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.memberRoutine.repository.MemberRoutineRepository;
import com.soptie.server.routine.entity.Routine;
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
}
