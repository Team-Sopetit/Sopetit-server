package com.soptie.server.persistence.adapter;

import static com.soptie.server.persistence.entity.deleted.RoutineType.*;

import java.util.Optional;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.persistence.entity.deleted.DeletedMemberRoutine;
import com.soptie.server.persistence.entity.deleted.MemberRoutine;
import com.soptie.server.persistence.repository.DeletedMemberRoutineRepository;
import com.soptie.server.persistence.repository.MemberRoutineRepository;

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
