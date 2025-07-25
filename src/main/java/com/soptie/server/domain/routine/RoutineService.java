package com.soptie.server.domain.routine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.routine.dto.GetRoutinesByMemberResponse;
import com.soptie.server.api.controller.routine.dto.GetRoutinesByThemeResponse;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {
	private final RoutineAdapter routineAdapter;
	private final MemberRoutineAdapter memberRoutineAdapter;

	public GetRoutinesByThemeResponse getRoutinesByThemeIds(Set<Long> themeIds) {
		val routinesByThemeId = new LinkedHashMap<Long, List<Routine>>();
		for (val themeId : themeIds) {
			val routines = routineAdapter.findByThemeId(themeId);
			routinesByThemeId.put(themeId, routines);
		}
		return GetRoutinesByThemeResponse.of(routinesByThemeId);
	}

	public GetRoutinesByMemberResponse getRoutinesByThemeId(long memberId, long themeId) {
		val routines = routineAdapter.findByThemeId(themeId);
		val memberRoutineIds = memberRoutineAdapter.findByMemberId(memberId).stream()
			.map(MemberRoutine::getRoutineId)
			.toList();
		return GetRoutinesByMemberResponse.of(getRoutineToMember(routines, memberRoutineIds));
	}

	private Map<Boolean, List<Routine>> getRoutineToMember(List<Routine> routines, List<Long> memberRoutineIds) {
		val routineToMember = new HashMap<Boolean, List<Routine>>();
		routineToMember.put(true, new ArrayList<>());
		routineToMember.put(false, new ArrayList<>());
		for (val routine : routines) {
			val isMemberRoutine = memberRoutineIds.contains(routine.getId());
			routineToMember.get(isMemberRoutine).add(routine);
		}
		return routineToMember;
	}
}
