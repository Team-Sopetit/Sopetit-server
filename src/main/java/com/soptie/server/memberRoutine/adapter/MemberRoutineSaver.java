package com.soptie.server.memberRoutine.adapter;

import java.util.Optional;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.DeletedMemberRoutine;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.memberRoutine.repository.DeletedMemberRoutineRepository;
import com.soptie.server.memberRoutine.repository.MemberRoutineRepository;
import com.soptie.server.routine.entity.Routine;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberRoutineSaver {

	private final MemberRoutineRepository memberRoutineRepository;
	private final DeletedMemberRoutineRepository deletedMemberRoutineRepository;

	public MemberRoutine checkHasDeletedAndSave(Member member, Routine routine) {
		return findDeleted(member, routine)
				.map(this::deleteHistoryAndSave)
				.orElseGet(() -> save(new MemberRoutine(member, routine)));
	}

	private MemberRoutine save(MemberRoutine memberRoutine) {
		return memberRoutineRepository.save(memberRoutine);
	}

	private Optional<DeletedMemberRoutine> findDeleted(Member member, Routine routine) {
		return deletedMemberRoutineRepository
				.findByMemberAndTypeAndRoutineId(member, routine.getType(), routine.getId());
	}

	private MemberRoutine deleteHistoryAndSave(DeletedMemberRoutine deletedMemberRoutine) {
		val memberRoutine = new MemberRoutine(deletedMemberRoutine);
		deletedMemberRoutineRepository.delete(deletedMemberRoutine);
		return save(memberRoutine);
	}
}
