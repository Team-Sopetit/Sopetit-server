package com.soptie.server.persistence.adapter;

import java.util.List;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.entity.routine.MemberRoutineEntity;
import com.soptie.server.persistence.repository.routine.MemberRoutineRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberRoutineAdapter {

	private final MemberRoutineRepository memberRoutineRepository;

	public List<MemberRoutine> saveAll(Member member, List<Routine> routines) {
		val deletedMemberRoutines = memberRoutineRepository.findDeletedByMemberId(member.getId());
		val deletedRoutineIds = deletedMemberRoutines
			.stream().map(MemberRoutineEntity::getRoutineId)
			.toList();

		return routines.stream()
			.map(routine -> deletedRoutineIds.contains(routine.getId())
				? restore(deletedMemberRoutines, member, routine).toDomain()
				: memberRoutineRepository.save(new MemberRoutineEntity(member, routine)).toDomain())
			.toList();
	}

	public List<MemberRoutine> findByMemberId(long memberId) {
		return memberRoutineRepository.findByMemberId(memberId).stream().map(MemberRoutineEntity::toDomain).toList();
	}

	public List<MemberRoutine> findByIds(List<Long> ids) {
		return memberRoutineRepository.findByIdIn(ids).stream().map(MemberRoutineEntity::toDomain).toList();
	}

	public void deleteAll(List<MemberRoutine> memberRoutines) {
		val memberRoutineIds = memberRoutines.stream().map(MemberRoutine::getId).toList();
		memberRoutineRepository.deleteAllByIdIn(memberRoutineIds);
	}

	public void deleteAllByMemberId(long memberId) {
		memberRoutineRepository.deleteAllByMemberId(memberId);
	}

	public MemberRoutine findById(long memberRoutineId) {
		return find(memberRoutineId).toDomain();
	}

	public void update(MemberRoutine memberRoutine) {
		val memberRoutineEntity = find(memberRoutine.getId());
		memberRoutineEntity.update(memberRoutine);
	}

	public void initAllAchievement() {
		memberRoutineRepository.bulkInitAchievement();
	}

	private MemberRoutineEntity restore(
		List<MemberRoutineEntity> deletedMemberRoutines,
		Member member,
		Routine routine
	) {
		val deletedMemberRoutine = deletedMemberRoutines.stream()
			.filter(mr -> mr.getRoutineId() == routine.getId())
			.findFirst()
			.orElseThrow(() -> new SoftieException(
				ExceptionCode.NOT_FOUND,
				"Member ID: " + member.getId() + " Routine ID: " + routine.getId()));
		deletedMemberRoutine.restore();
		return deletedMemberRoutine;
	}

	private MemberRoutineEntity find(long id) {
		return memberRoutineRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "MemberRoutine ID: " + id));
	}
}
