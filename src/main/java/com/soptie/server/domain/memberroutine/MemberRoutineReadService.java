package com.soptie.server.domain.memberroutine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.global.ThemeStore;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberRoutineReadService {

	private final MemberRoutineAdapter memberRoutineAdapter;
	private final ThemeStore themeStore;

	public Map<Theme, List<MemberRoutine>> getAllWithTheme(long memberId) {
		List<MemberRoutine> memberRoutines = memberRoutineAdapter.findByMemberId(memberId);
		Map<Long, Theme> themeMap = getThemeMap(memberRoutines);
		return memberRoutines.stream()
			.filter(routine -> themeMap.containsKey(routine.getThemeId()))
			.collect(Collectors.groupingBy(routine -> themeMap.get(routine.getThemeId())));
	}

	private Map<Long, Theme> getThemeMap(List<MemberRoutine> memberRoutines) {
		return memberRoutines.stream()
			.map(MemberRoutine::getThemeId)
			.distinct()
			.map(id -> Map.entry(id, themeStore.get(id)))
			.filter(entry -> entry.getKey() != null && entry.getValue() != null)
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
