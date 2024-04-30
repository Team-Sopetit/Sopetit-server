package com.soptie.server.routine.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.routine.adapter.RoutineFinder;
import com.soptie.server.routine.service.dto.request.DailyRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.response.DailyRoutineListGetServiceResponse;
import com.soptie.server.theme.adapter.ThemeFinder;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {

	private final RoutineFinder routineFinder;
	private final ThemeFinder themeFinder;
	private final MemberFinder memberFinder;

	public DailyRoutineListGetServiceResponse getRoutinesByThemes(List<Long> themeIds) {
		val routines = routineFinder.findDailyRoutinesByThemeIds(themeIds);
		return DailyRoutineListGetServiceResponse.of(routines);
	}

	public DailyRoutineListGetServiceResponse getRoutinesByTheme(DailyRoutineListGetServiceRequest request) {
		val theme = themeFinder.findById(request.themeId());
		val member = memberFinder.findById(request.memberId());
		val routines = routineFinder.findDailyRoutinesByThemeAndNotMember(theme, member);
		return DailyRoutineListGetServiceResponse.of(routines, theme);
	}
}
