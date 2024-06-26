package com.soptie.server.routine.service;

import static com.soptie.server.routine.entity.RoutineType.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.routine.adapter.ChallengeFinder;
import com.soptie.server.routine.adapter.RoutineFinder;
import com.soptie.server.routine.service.dto.request.DailyRoutineListByThemeGetServiceRequest;
import com.soptie.server.routine.service.dto.request.DailyRoutineListByThemesGetServiceRequest;
import com.soptie.server.routine.service.dto.request.HappinessRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.request.HappinessSubRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.response.DailyRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessSubRoutineListGetServiceResponse;
import com.soptie.server.routine.service.vo.RoutineVO;
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
	private final ChallengeFinder challengeFinder;

	public DailyRoutineListGetServiceResponse getRoutinesByThemes(DailyRoutineListByThemesGetServiceRequest request) {
		val routines = routineFinder.findDailyRoutinesByThemeIds(request.themeIds());
		return DailyRoutineListGetServiceResponse.of(routines);
	}

	public DailyRoutineListGetServiceResponse getRoutinesByTheme(DailyRoutineListByThemeGetServiceRequest request) {
		val theme = themeFinder.findById(request.themeId());
		val member = memberFinder.findById(request.memberId());
		val routines = routineFinder.findDailyRoutinesByThemeAndNotMember(theme, member);
		return DailyRoutineListGetServiceResponse.of(routines, theme);
	}

	public HappinessRoutineListGetServiceResponse getHappinessRoutinesByTheme(
		HappinessRoutineListGetServiceRequest request
	) {
		val routines = routineFinder.findChallengeRoutinesByTheme(request.themeId());
		return HappinessRoutineListGetServiceResponse.of(routines);
	}

	public HappinessSubRoutineListGetServiceResponse getHappinessSubRoutines(
		HappinessSubRoutineListGetServiceRequest request
	) {
		val routine = routineFinder.findById(request.routineId());
		val subRoutines = challengeFinder.findByRoutine(routine);
		return HappinessSubRoutineListGetServiceResponse.of(routine, subRoutines);
	}

	public Map<Long, List<RoutineVO>> acquireAllInDailyWithThemeId(Set<Long> themeIds) {
		val themeToRoutine = new LinkedHashMap<Long, List<RoutineVO>>();
		for (val themeId : themeIds) {
			val routines = routineFinder.findAllByTypeAndThemeId(DAILY, themeId);
			themeToRoutine.put(themeId, routines);
		}
		return themeToRoutine;
	}
}
