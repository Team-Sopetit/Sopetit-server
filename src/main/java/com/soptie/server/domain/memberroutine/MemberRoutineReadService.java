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
		return memberRoutineAdapter.findByMemberId(memberId)
			.stream()
			.collect(Collectors.groupingBy(routine -> themeStore.get(routine.getThemeId())));
	}
}
