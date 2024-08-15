package com.soptie.server.memberroutine.service;

import static com.soptie.server.routine.message.RoutineErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberroutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberroutine.adapter.MemberRoutineSaver;
import com.soptie.server.memberroutine.controller.v1.dto.request.MemberDailyRoutinesCreateRequest;
import com.soptie.server.memberroutine.service.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberroutine.service.dto.request.MemberHappinessRoutineCreateServiceRequest;
import com.soptie.server.memberroutine.service.dto.response.MemberDailyRoutineCreateServiceResponse;
import com.soptie.server.memberroutine.service.dto.response.MemberHappinessRoutineCreateServiceResponse;
import com.soptie.server.routine.adapter.ChallengeFinder;
import com.soptie.server.routine.adapter.RoutineFinder;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.exception.RoutineException;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberRoutineCreateService {

	private final MemberRoutineSaver memberRoutineSaver;
	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberFinder memberFinder;
	private final RoutineFinder routineFinder;
	private final ChallengeFinder challengeFinder;

	public MemberDailyRoutineCreateServiceResponse createDailyRoutine(MemberDailyRoutineCreateServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val routine = routineFinder.findById(request.routineId());
		checkMemberHasSameRoutineAlready(member, routine);
		val savedMemberRoutine = memberRoutineSaver.checkHasDeletedAndSave(member, routine);
		return MemberDailyRoutineCreateServiceResponse.of(savedMemberRoutine);
	}

	public void createDailyRoutines(long memberId, MemberDailyRoutinesCreateRequest request) {
		val member = memberFinder.findById(memberId);
		val routines = routineFinder.findDailyByIds(request.routineIds());
		routines.forEach(routine -> memberRoutineSaver.checkHasDeletedAndSave(member, routine));
	}

	public MemberHappinessRoutineCreateServiceResponse createHappinessRoutine(
		MemberHappinessRoutineCreateServiceRequest request
	) {
		val member = memberFinder.findById(request.memberId());
		checkMemberHasChallengeAlready(member);
		val challenge = challengeFinder.findById(request.challengeId());
		val savedMemberRoutine = memberRoutineSaver.checkHasDeletedAndSave(member, challenge);
		return MemberHappinessRoutineCreateServiceResponse.of(savedMemberRoutine);
	}

	private void checkMemberHasSameRoutineAlready(Member member, Routine routine) {
		if (memberRoutineFinder.isExist(member, routine)) {
			throw new RoutineException(DUPLICATED_ROUTINE);
		}
	}

	private void checkMemberHasChallengeAlready(Member member) {
		if (memberRoutineFinder.existMemberChallenge(member)) {
			throw new RoutineException(CANNOT_ADD_MEMBER_ROUTINE);
		}
	}
}
