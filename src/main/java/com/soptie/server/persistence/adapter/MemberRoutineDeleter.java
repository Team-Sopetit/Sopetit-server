package com.soptie.server.persistence.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.entity.DeletedMemberRoutine;
import com.soptie.server.persistence.entity.MemberRoutine;
import com.soptie.server.persistence.repository.DeletedMemberRoutineRepository;
import com.soptie.server.persistence.repository.MemberRoutineRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberRoutineDeleter {

	private final MemberRoutineRepository memberRoutineRepository;
	private final DeletedMemberRoutineRepository deletedMemberRoutineRepository;

	public void softDelete(MemberRoutine memberRoutine) {
		deletedMemberRoutineRepository.save(new DeletedMemberRoutine(memberRoutine));
		memberRoutineRepository.delete(memberRoutine);
	}

	public void deleteByMember(Member member) {
		memberRoutineRepository.deleteByMember(member);
		deletedMemberRoutineRepository.deleteByMember(member);
	}
}
