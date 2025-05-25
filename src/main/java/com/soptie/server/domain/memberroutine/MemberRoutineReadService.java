package com.soptie.server.domain.memberroutine;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.domain.routine.Routine;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.global.RoutineStore;
import com.soptie.server.persistence.global.ThemeStore;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberRoutineReadService {

	private final MemberRoutineAdapter memberRoutineAdapter;

	private final RoutineStore routineStore;
	private final ThemeStore themeStore;

	public Map<Theme, List<MemberRoutine>> getAllWithTheme(long memberId) {
		return memberRoutineAdapter.findByMemberId(memberId)
			.stream()
			.map(this::mergeWithRoutine)
			.filter(Objects::nonNull)
			.filter(routine -> routine.getThemeId() != null)
			.collect(Collectors.groupingBy(routine -> themeStore.get(routine.getThemeId())));
	}

	private MemberRoutine mergeWithRoutine(MemberRoutine memberRoutine) {
		// custom routine
		if (memberRoutine.getRoutineId() == null) {
			return memberRoutine;
		}

		// system routine
		Routine routine = routineStore.get(memberRoutine.getRoutineId());
		if (routine == null) {
			return null;
		}

		memberRoutine.setContent(routine.getContent());
		memberRoutine.setThemeId(routine.getThemeId());
		return memberRoutine;
	}
}
