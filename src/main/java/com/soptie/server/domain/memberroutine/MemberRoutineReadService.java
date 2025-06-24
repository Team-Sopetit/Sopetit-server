package com.soptie.server.domain.memberroutine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.commons.lang.Pair;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.adapter.ThemeAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberRoutineReadService {

	private final MemberRoutineAdapter memberRoutineAdapter;

	private final ThemeAdapter themeAdapter;

	//todo. findByIdsIn 리팩토링
	public Map<Theme, List<MemberRoutine>> getAllWithTheme(long memberId) {
		Map<Long, Theme> themeMap = themeAdapter.findAll()
			.stream()
			.map(it -> Pair.of(it.getId(), it))
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

		return memberRoutineAdapter.findByMemberId(memberId)
			.stream()
			.collect(Collectors.groupingBy(routine -> themeMap.get(routine.getThemeId())));
	}
}
