package com.soptie.server.domain.memberroutine;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.adapter.MemberFinder;
import com.soptie.server.persistence.adapter.MemberRoutineFinder;
import com.soptie.server.persistence.repository.dto.MemberRoutineResponse;

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

	public Optional<MemberChallengeRoutineAcquireServiceResponse> acquire(
		MemberChallengeRoutineAcquireServiceRequest request
	) {
		val member = memberFinder.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findChallengeByMember(member);
		return memberRoutine.map(MemberChallengeRoutineAcquireServiceResponse::of);
	}

	private List<MemberDailyRoutinesAcquireServiceResponse> collectByTheme(List<MemberRoutineResponse> routines) {
		val routinesByTheme = routines.stream().collect(Collectors.groupingBy(MemberRoutineResponse::themeId));
		return routinesByTheme.values().stream()
			.map(MemberDailyRoutinesAcquireServiceResponse::of).toList();
	}
}
