package com.soptie.server.routine.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.soptie.server.base.BaseServiceTest;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.entity.SocialType;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.routine.dto.DailyRoutinesByThemeResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.entity.daily.RoutineImage;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;

import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class DailyRoutineServiceTest extends BaseServiceTest {

	@InjectMocks
	private DailyRoutineServiceImpl dailyRoutineService;

	@Mock
	private DailyRoutineRepository dailyRoutineRepository;
	@Mock
	private DailyThemeRepository dailyThemeRepository;
	@Mock
	private MemberRepository memberRepository;

	private final Member MEMBER = member();
	private final DailyTheme THEME = theme();
	private final int LIST_SIZE = 5;
	private final String ROUTINE_CONTENT = "오늘의 음악 선곡하기";
	private final String THEME_NAME = "소중한 나";

	@DisplayName("데일리 루틴 테마 조회")
	@Test
	void success_getDailyThemes() {
		// given
		List<DailyTheme> themes = themeList();

		when(dailyThemeRepository.findAllOrderByNameAsc()).thenReturn(themes);

		// when
		DailyThemesResponse response = dailyRoutineService.getThemes();

		// then
		assertThat(response.themes().size(), is(equalTo(LIST_SIZE)));
		assertThat(response.themes().get(0).name(), is(equalTo(THEME_NAME)));
	}

	@DisplayName("테마별 데일리 루틴 조회")
	@Test
	void success_getRoutinesByTheme() {
		// given
		long memberId = 0L;
		long themeId = 1L;
		List<DailyRoutine> routines = routineList();

		when(memberRepository.findById(memberId)).thenReturn(Optional.of(MEMBER));
		when(dailyThemeRepository.findById(themeId)).thenReturn(Optional.of(THEME));
		when(dailyRoutineRepository.findAllByTheme(THEME)).thenReturn(routines);

		// when
		DailyRoutinesByThemeResponse response = dailyRoutineService.getRoutinesByTheme(memberId, themeId);

		// then
		assertThat(response.routines().size(), is(equalTo(LIST_SIZE)));
		assertThat(response.routines().get(0).content(), is(equalTo(ROUTINE_CONTENT)));

		// verify
		verify(dailyThemeRepository, times(1)).findById(themeId);
		verify(dailyRoutineRepository, times(1)).findAllByTheme(THEME);
	}

	private List<DailyRoutine> routineList() {
		return Stream.iterate(1, i -> i + 1).limit(LIST_SIZE)
				.map(i -> routine()).toList();
	}

	private DailyRoutine routine() {
		return DailyRoutine.builder()
				.content(ROUTINE_CONTENT)
				.theme(THEME)
				.build();
	}

	private Member member() {
		return Member.builder()
				.socialType(SocialType.KAKAO)
				.socialId("socialId")
				.build();
	}

	private List<DailyTheme> themeList() {
		return Stream.iterate(1, i -> i + 1).limit(LIST_SIZE)
				.map(i -> theme()).toList();
	}

	private DailyTheme theme() {
		return DailyTheme.builder()
				.name(THEME_NAME)
				.imageInfo(imageInfo())
				.build();
	}

	private RoutineImage imageInfo() {
		return RoutineImage.builder()
				.iconImageUrl("https://...")
				.backgroundImageUrl("https://...")
				.build();
	}
}