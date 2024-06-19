package com.soptie.server.memberRoutine.service;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.memberRoutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberRoutine.repository.dto.MemberRoutineResponse;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberHappinessRoutineGetServiceRequest;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineWithThemeGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineWithThemeListGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberHappinessRoutineGetServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRoutineReadService {

	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberFinder memberFinder;

	public MemberDailyRoutineListGetServiceResponse getDailyRoutines(MemberDailyRoutineListGetServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val routines = memberRoutineFinder.findAllByMember(member);
		return MemberDailyRoutineListGetServiceResponse.of(routines);
	}

	public Optional<MemberHappinessRoutineGetServiceResponse> getHappinessRoutine(
			MemberHappinessRoutineGetServiceRequest request
	) {
		val member = memberFinder.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findChallengeByMember(member);
		return memberRoutine.map(MemberHappinessRoutineGetServiceResponse::of);
	}

	public MemberDailyRoutineWithThemeListGetServiceResponse acquire(
			MemberDailyRoutineListGetServiceRequest request
	) {
		val member = memberFinder.findById(request.memberId());
		val routines = memberRoutineFinder.findAllByMember(member);
		val routinesWithTheme = collectByTheme(routines);
        return MemberDailyRoutineWithThemeListGetServiceResponse.of(routinesWithTheme);
	}

	private List<MemberDailyRoutineWithThemeGetServiceResponse> collectByTheme(
			List<MemberRoutineResponse> routines
	) {
		val routinesByTheme = routines.stream().collect(Collectors.groupingBy(MemberRoutineResponse::themeId));
        return routinesByTheme.values().stream()
				.map(MemberDailyRoutineWithThemeGetServiceResponse::of).toList();
	}
}
