package com.soptie.server.memberRoutine.service.v1;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.adapter.MemberRoutineDeleter;
import com.soptie.server.memberRoutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.memberRoutine.service.v1.dto.request.MemberRoutineDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.v1.dto.request.MemberRoutinesDeleteServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberRoutineDeleteService {

	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberRoutineDeleter memberRoutineDeleter;
	private final MemberFinder memberFinder;

	public void deleteMemberRoutines(MemberRoutinesDeleteServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val routines = filterMemberRoutines(member, request.routineIds());
		routines.forEach(memberRoutineDeleter::softDelete);
	}

	public void deleteMemberRoutine(MemberRoutineDeleteServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findById(request.routineId());
		memberRoutine.checkMemberHas(member);
		memberRoutineDeleter.softDelete(memberRoutine);
	}

	private List<MemberRoutine> filterMemberRoutines(Member member, List<Long> routineIds) {
		return routineIds.stream()
				.map(memberRoutineFinder::findById)
				.filter(routine -> routine.getMember().equals(member))
				.toList();
	}
}
