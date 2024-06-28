package com.soptie.server.routine.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.routine.adapter.ChallengeFinder;
import com.soptie.server.routine.adapter.RoutineFinder;
import com.soptie.server.routine.entity.RoutineType;
import com.soptie.server.routine.service.dto.request.HappinessSubRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.response.HappinessSubRoutineListGetServiceResponse;
import com.soptie.server.routine.service.vo.RoutineVO;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {

	private final RoutineFinder routineFinder;
	private final ChallengeFinder challengeFinder;

	public List<RoutineVO> acquireAllInDailyByThemeIds(List<Long> themeIds) {
		return routineFinder.findAllByTypeAndThemeIds(RoutineType.DAILY, themeIds);
	}

	public List<RoutineVO> acquireAllInDailyNotInMemberByThemeId(long memberId, long themeId) {
		return routineFinder.findAllNotInMemberByTypeAndThemeId(memberId, RoutineType.DAILY, themeId);
	}

	public List<RoutineVO> acquireAllInHappinessByThemeId(Long themeId) {
		return routineFinder.findAllByTypeAndThemeId(RoutineType.CHALLENGE, themeId);
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
			val routines = routineFinder.findAllByTypeAndThemeId(RoutineType.DAILY, themeId);
			themeToRoutine.put(themeId, routines);
		}
		return themeToRoutine;
	}
}
