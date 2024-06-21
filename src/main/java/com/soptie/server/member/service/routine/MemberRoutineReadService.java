package com.soptie.server.member.service.routine;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.adapter.MemberRoutineFinder;
import com.soptie.server.member.service.dto.request.routine.daily.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.member.service.dto.request.routine.happiness.MemberHappinessRoutineGetServiceRequest;
import com.soptie.server.member.service.dto.response.routine.daily.MemberDailyRoutineListGetServiceResponse;
import com.soptie.server.member.service.dto.response.routine.happiness.MemberHappinessRoutineGetServiceResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRoutineReadService {

	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberFinder memberFinder;

	public MemberDailyRoutineListGetServiceResponse getDailyRoutines(MemberDailyRoutineListGetServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val routines = memberRoutineFinder.findDailyRoutinesByMember(member);
		return MemberDailyRoutineListGetServiceResponse.of(routines);
	}

	public Optional<MemberHappinessRoutineGetServiceResponse> getHappinessRoutine(
		MemberHappinessRoutineGetServiceRequest request
	) {
		val member = memberFinder.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findChallengeByMember(member);
		return memberRoutine.map(MemberHappinessRoutineGetServiceResponse::of);
	}
}
