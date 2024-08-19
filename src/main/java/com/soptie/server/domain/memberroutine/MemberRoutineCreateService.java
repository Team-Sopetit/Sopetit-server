package com.soptie.server.domain.memberroutine;

import static com.soptie.server.common.message.RoutineErrorCode.CANNOT_ADD_MEMBER_ROUTINE;
import static com.soptie.server.common.message.RoutineErrorCode.DUPLICATED_ROUTINE;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.adapter.MemberFinder;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.adapter.MemberRoutineFinder;
import com.soptie.server.persistence.adapter.MemberRoutineSaver;
import com.soptie.server.api.controller.dto.request.memberroutine.MemberDailyRoutinesCreateRequest;
import com.soptie.server.persistence.adapter.ChallengeFinder;
import com.soptie.server.persistence.adapter.RoutineFinder;
import com.soptie.server.persistence.entity.Routine;
import com.soptie.server.common.exception.RoutineException;

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

	public MemberDailyRoutinesCreateServiceResponse createDailyRoutines(
		long memberId, MemberDailyRoutinesCreateRequest request
	) {
		val member = memberFinder.findById(memberId);
		val routines = routineFinder.findDailyByIds(request.routineIds());
		routines.forEach(routine -> checkMemberHasSameRoutineAlready(member, routine));
		val memberRoutines = routines.stream()
			.map(routine -> memberRoutineSaver.checkHasDeletedAndSave(member, routine))
			.toList();
		return MemberDailyRoutinesCreateServiceResponse.of(memberRoutines);
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
