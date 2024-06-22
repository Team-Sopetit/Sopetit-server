package com.soptie.server.memberroutine.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.memberroutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberroutine.repository.dto.MemberRoutineResponse;
import com.soptie.server.memberroutine.service.dto.request.MemberDailyRoutineListAcquireServiceRequest;
import com.soptie.server.memberroutine.service.dto.request.MemberHappinessRoutineGetServiceRequest;
import com.soptie.server.memberroutine.service.dto.response.MemberDailyRoutineListAcquireServiceResponse;
import com.soptie.server.memberroutine.service.dto.response.MemberDailyRoutinesAcquireServiceResponse;
import com.soptie.server.memberroutine.service.dto.response.MemberHappinessRoutineGetServiceResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRoutineReadService {

	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberFinder memberFinder;

	public MemberDailyRoutinesAcquireServiceResponse getDailyRoutines(
		MemberDailyRoutineListAcquireServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val routines = memberRoutineFinder.findAllByMember(member);
		return MemberDailyRoutinesAcquireServiceResponse.of(routines);
	}

	public Optional<MemberHappinessRoutineGetServiceResponse> getHappinessRoutine(
		MemberHappinessRoutineGetServiceRequest request
	) {
		val member = memberFinder.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findChallengeByMember(member);
		return memberRoutine.map(MemberHappinessRoutineGetServiceResponse::of);
	}

	public MemberDailyRoutineListAcquireServiceResponse acquireAll(
		MemberDailyRoutineListAcquireServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val routines = memberRoutineFinder.findAllByMember(member);
		val routinesWithTheme = collectByTheme(routines);
		return MemberDailyRoutineListAcquireServiceResponse.of(routinesWithTheme);
	}

	private List<MemberDailyRoutinesAcquireServiceResponse> collectByTheme(List<MemberRoutineResponse> routines) {
		val routinesByTheme = routines.stream().collect(Collectors.groupingBy(MemberRoutineResponse::themeId));
		return routinesByTheme.values().stream()
			.map(MemberDailyRoutinesAcquireServiceResponse::of).toList();
	}
}
