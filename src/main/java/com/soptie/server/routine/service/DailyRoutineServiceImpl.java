package com.soptie.server.routine.service;

import static com.soptie.server.member.message.ErrorCode.*;
import static com.soptie.server.routine.message.ErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.routine.dto.daily.DailyRoutinesByThemeGetResponse;
import com.soptie.server.routine.dto.daily.DailyRoutinesByThemesGetResponse;
import com.soptie.server.routine.dto.daily.DailyThemesGetResponse;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;

import lombok.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DailyRoutineServiceImpl implements DailyRoutineService {

	private final DailyThemeRepository dailyThemeRepository;
	private final DailyRoutineRepository dailyRoutineRepository;
	private final MemberRepository memberRepository;

	@Override
	public DailyThemesGetResponse getThemes() {
		val themes = dailyThemeRepository.findAllOrderByNameAsc();
		return DailyThemesGetResponse.of(themes);
	}

	@Override
	public DailyRoutinesByThemesGetResponse getRoutinesByThemes(List<Long> themeIds) {
		val routines = dailyRoutineRepository.findAllByThemes(themeIds);
		return DailyRoutinesByThemesGetResponse.of(routines);
	}

	@Override
	public DailyRoutinesByThemeGetResponse getRoutinesByTheme(long memberId, long themeId) {
		val member = findMember(memberId);
		val theme = findTheme(themeId);
		val routines = getRoutines(member, theme);
		return DailyRoutinesByThemeGetResponse.of(theme, routines);
	}

	private Member findMember(long id) {
		return memberRepository.findById(id)
				.orElseThrow(() -> new MemberException(INVALID_MEMBER));
	}

	private DailyTheme findTheme(long id) {
		return dailyThemeRepository.findById(id)
				.orElseThrow(() -> new RoutineException(INVALID_THEME));
	}

	private List<DailyRoutine> getRoutines(Member member, DailyTheme theme) {
		return dailyRoutineRepository.findAllByTheme(theme).stream()
				.filter(member::isNotExistRoutine)
				.toList();
	}
}
