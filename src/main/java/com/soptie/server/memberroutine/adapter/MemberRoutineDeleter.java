package com.soptie.server.memberroutine.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberroutine.entity.DeletedMemberRoutine;
import com.soptie.server.memberroutine.entity.MemberRoutine;
import com.soptie.server.memberroutine.repository.DeletedMemberRoutineRepository;
import com.soptie.server.memberroutine.repository.MemberRoutineRepository;

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
