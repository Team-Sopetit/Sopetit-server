package com.soptie.server.persistence.adapter.routine;

import java.util.List;
import java.util.Objects;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.entity.routine.MemberRoutineEntity;
import com.soptie.server.persistence.global.RoutineStore;
import com.soptie.server.persistence.repository.routine.MemberRoutineRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberRoutineAdapter {

	private final MemberRoutineRepository memberRoutineRepository;

	private final RoutineStore routineStore;

	/**
	 * save
	 */

	public MemberRoutine save(MemberRoutine memberRoutine) {
		MemberRoutineEntity savedEntity = memberRoutineRepository.save(new MemberRoutineEntity(memberRoutine));
		return savedEntity.toDomain();
	}

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

	/**
	 * find
	 */

	public List<MemberRoutine> findAllByRoutineIds(long memberId, List<Long> routineIds) {
		return memberRoutineRepository.findByMemberIdAndRoutineIdIn(memberId, routineIds)
			.stream()
			.map(MemberRoutineEntity::toDomain)
			.map(this::mergeWithRoutine)
			.filter(Objects::nonNull)
			.filter(routine -> routine.getThemeId() != null)
			.toList();
	}

	public List<MemberRoutine> findByMemberId(long memberId) {
		return memberRoutineRepository.findByMemberId(memberId)
			.stream()
			.map(MemberRoutineEntity::toDomain)
			.map(this::mergeWithRoutine)
			.filter(Objects::nonNull)
			.filter(routine -> routine.getThemeId() != null)
			.toList();
	}

	public List<MemberRoutine> findByIds(List<Long> ids) {
		return memberRoutineRepository.findByIdIn(ids)
			.stream()
			.map(MemberRoutineEntity::toDomain)
			.map(this::mergeWithRoutine)
			.filter(Objects::nonNull)
			.filter(routine -> routine.getThemeId() != null)
			.toList();
	}

	public List<MemberRoutine> findByIdIn(List<Long> ids) {
		return memberRoutineRepository.findByIdIn(ids)
			.stream()
			.map(MemberRoutineEntity::toDomain)
			.toList();
	}

	/**
	 * delete
	 */

	public void deleteAll(List<MemberRoutine> memberRoutines) {
		val memberRoutineIds = memberRoutines.stream().map(MemberRoutine::getId).toList();
		memberRoutineRepository.deleteAllByIdIn(memberRoutineIds);
	}

	public void deleteForce(MemberRoutine memberRoutine) {
		memberRoutineRepository.deleteForceById(memberRoutine.getId());
	}

	public void deleteAllByMemberId(long memberId) {
		memberRoutineRepository.deleteAllByMemberId(memberId);
	}

	public MemberRoutine findById(long memberRoutineId) {
		return mergeWithRoutine(find(memberRoutineId).toDomain());
	}

	/**
	 * update
	 */

	public void update(MemberRoutine memberRoutine) {
		val memberRoutineEntity = find(memberRoutine.getId());
		memberRoutineEntity.update(memberRoutine);
	}

	public MemberRoutine updateAll(MemberRoutine memberRoutine) {
		MemberRoutineEntity memberRoutineEntity = find(memberRoutine.getId());
		memberRoutineEntity.updateAll(memberRoutine);
		return mergeWithRoutine(memberRoutineEntity.toDomain());
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
			.filter(mr -> Objects.equals(mr.getRoutineId(), routine.getId()))
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

	private MemberRoutine mergeWithRoutine(MemberRoutine memberRoutine) {
		// custom routine
		if (memberRoutine.getRoutineId() == null) {
			return memberRoutine;
		}

		// system routine
		Routine routine = routineStore.get(memberRoutine.getRoutineId());
		if (routine == null) {
			return null;
		}

		memberRoutine.setContent(routine.getContent());
		memberRoutine.setThemeId(routine.getThemeId());
		return memberRoutine;
	}
}
