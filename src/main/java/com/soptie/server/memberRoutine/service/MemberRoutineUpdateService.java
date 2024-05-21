package com.soptie.server.memberRoutine.service;

import static com.soptie.server.routine.entity.RoutineType.*;
import static com.soptie.server.routine.message.RoutineErrorCode.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.adapter.MemberRoutineDeleter;
import com.soptie.server.memberRoutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberRoutine.adapter.MemberRoutineSaver;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberHappinessRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberHappinessRoutineGetServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutineDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutinesDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineCreateServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberHappinessRoutineCreateServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberHappinessRoutineGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberRoutineAchieveServiceResponse;
import com.soptie.server.routine.adapter.ChallengeFinder;
import com.soptie.server.routine.adapter.RoutineFinder;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.exception.RoutineException;

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
