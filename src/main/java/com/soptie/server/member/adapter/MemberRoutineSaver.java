package com.soptie.server.member.adapter;

import static com.soptie.server.routine.entity.RoutineType.*;

import java.util.Optional;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.DeletedMemberRoutine;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.entity.MemberRoutine;
import com.soptie.server.member.repository.DeletedMemberRoutineRepository;
import com.soptie.server.member.repository.MemberRoutineRepository;
import com.soptie.server.routine.entity.Challenge;
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

	public MemberRoutine checkHasDeletedAndSave(Member member, Challenge challenge) {
		return findDeleted(member, challenge)
			.map(this::deleteHistoryAndSave)
			.orElseGet(() -> save(new MemberRoutine(member, challenge)));
	}

	private MemberRoutine save(MemberRoutine memberRoutine) {
		return memberRoutineRepository.save(memberRoutine);
	}

	private Optional<DeletedMemberRoutine> findDeleted(Member member, Routine routine) {
		return deletedMemberRoutineRepository
			.findByMemberAndTypeAndRoutineId(member, routine.getType(), routine.getId());
	}

	private Optional<DeletedMemberRoutine> findDeleted(Member member, Challenge challenge) {
		return deletedMemberRoutineRepository
			.findByMemberAndTypeAndRoutineId(member, CHALLENGE, challenge.getId());
	}

	private MemberRoutine deleteHistoryAndSave(DeletedMemberRoutine deletedMemberRoutine) {
		val memberRoutine = new MemberRoutine(deletedMemberRoutine);
		deletedMemberRoutineRepository.delete(deletedMemberRoutine);
		return save(memberRoutine);
	}
}
