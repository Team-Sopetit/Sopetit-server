package com.soptie.server.member.service.routine;

import static com.soptie.server.routine.entity.RoutineType.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.adapter.MemberRoutineDeleter;
import com.soptie.server.member.adapter.MemberRoutineFinder;
import com.soptie.server.member.entity.MemberRoutine;
import com.soptie.server.member.service.dto.request.routine.MemberRoutineAchieveServiceRequest;
import com.soptie.server.member.service.dto.response.routine.MemberRoutineAchieveServiceResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberRoutineUpdateService {

	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberRoutineDeleter memberRoutineDeleter;
	private final MemberFinder memberFinder;

	public MemberRoutineAchieveServiceResponse achieveMemberRoutine(MemberRoutineAchieveServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findById(request.memberRoutineId());
		memberRoutine.checkMemberHas(member);
		memberRoutine.achieve();
		member.addCottonCount(memberRoutine.getType());
		deleteMemberRoutineIfTypeIsOneTime(memberRoutine);
		return MemberRoutineAchieveServiceResponse.of(memberRoutine);
	}

	public void initDailyRoutines() {
		val routines = memberRoutineFinder.findAchieved();
		routines.forEach(MemberRoutine::initAchieve);
	}

	private void deleteMemberRoutineIfTypeIsOneTime(MemberRoutine memberRoutine) {
		if (memberRoutine.getType().equals(CHALLENGE)) {
			memberRoutineDeleter.softDelete(memberRoutine);
		}
	}
}
