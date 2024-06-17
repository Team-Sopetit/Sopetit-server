package com.soptie.server.memberRoutine.service.v1;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.memberRoutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberRoutine.service.v1.dto.request.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.memberRoutine.service.v1.dto.request.MemberHappinessRoutineGetServiceRequest;
import com.soptie.server.memberRoutine.service.v1.dto.response.MemberDailyRoutineListGetServiceResponse;
import com.soptie.server.memberRoutine.service.v1.dto.response.MemberHappinessRoutineGetServiceResponse;

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
