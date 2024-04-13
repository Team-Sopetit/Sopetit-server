package com.soptie.server.routine.service;

import static com.soptie.server.member.message.ErrorCode.*;
import static com.soptie.server.routine.message.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.routine.dto.DailyRoutineResponse;
import com.soptie.server.routine.dto.DailyRoutinesByThemeResponse;
import com.soptie.server.routine.dto.DailyRoutinesByThemesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse.DailyThemeResponse;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;
import com.soptie.server.support.fixture.DailyRoutineFixture;
import com.soptie.server.support.fixture.DailyThemeFixture;
import com.soptie.server.support.fixture.MemberDailyRoutineFixture;
import com.soptie.server.support.fixture.MemberFixture;

@ExtendWith(MockitoExtension.class)
class DailyRoutineServiceImplTest {

	@InjectMocks
	private DailyRoutineServiceImpl dailyRoutineService;

	@Mock
	private DailyThemeRepository dailyThemeRepository;
	@Mock
	private DailyRoutineRepository dailyRoutineRepository;
	@Mock
	private MemberRepository memberRepository;

	@Test
	void 모든_데일리루틴의_테마정보를_조회한다() {
		// given
		String imageUrl = "https://www...";
		List<DailyTheme> dailyThemes = List.of(
				DailyThemeFixture.dailyTheme().id(1L).name("theme1").imageUrl(imageUrl).build(),
				DailyThemeFixture.dailyTheme().id(2L).name("theme2").imageUrl(imageUrl).build()
		);
		doReturn(dailyThemes).when(dailyThemeRepository).findAllOrderByNameAsc();

		// when
		final DailyThemesResponse actual = dailyRoutineService.getThemes();

		// then
		List<Long> themeIds = actual.themes().stream().map(DailyThemeResponse::themeId).toList();
		assertThat(themeIds).containsExactlyInAnyOrder(1L, 2L);
	}

	@Test
	void 테마_리스트로_조회하면_테마_리스트에_포함된_모든_데일리루틴_정보를_조회한다() {
		// given
		List<Long> themeIds = List.of(1L, 2L);
		DailyTheme theme1 = DailyThemeFixture.dailyTheme().id(1L).imageUrl("https://www...").build();
		DailyTheme theme2 = DailyThemeFixture.dailyTheme().id(2L).imageUrl("https://www...").build();
		List<DailyRoutine> routines = List.of(
				DailyRoutineFixture.dailyRoutine().id(1L).content("routine1").theme(theme1).build(),
				DailyRoutineFixture.dailyRoutine().id(2L).content("routine2").theme(theme2).build()
		);
		doReturn(routines).when(dailyRoutineRepository).findAllByThemes(themeIds);

		// when
		final DailyRoutinesByThemesResponse actual = dailyRoutineService.getRoutinesByThemes(themeIds);

		// then
		List<Long> routineIds = actual.routines().stream().map(DailyRoutineResponse::routineId).toList();
		assertThat(routineIds).containsExactlyInAnyOrder(1L, 2L);
	}

	@Test
	void 존재하지_않는_회원이면_예외가_발생한다() {
		// given
		doReturn(Optional.empty()).when(memberRepository).findById(anyLong());

		// when & then
		assertThatThrownBy(() -> dailyRoutineService.getRoutinesByTheme(1L, 1L))
				.isInstanceOf(MemberException.class)
				.hasMessage("[MemberException] : " + INVALID_MEMBER.getMessage());
	}

	@Test
	void 존재하지_않는_테마이면_예외가_발생한다() {
		// given
		member(1L);
		doReturn(Optional.empty()).when(dailyThemeRepository).findById(anyLong());

		// when & then
		assertThatThrownBy(() -> dailyRoutineService.getRoutinesByTheme(1L, 1L))
				.isInstanceOf(RoutineException.class)
				.hasMessage("[RoutineException] : " + INVALID_THEME.getMessage());
	}

	@DisplayName("테마별 데일리 루틴 조회 (회원에게 없는 루틴만 조회)")
	@Test
	void 테마로_조회하면_테마에_포함되고_회원이_추가하지_않은_데일리루틴들을_조회한다() {
		// given
		long memberId = 2L;
		Member member = member(memberId);

		long themeId = 1L;
		DailyTheme theme = dailyTheme(themeId);

		List<DailyRoutine> routines = List.of(
				DailyRoutineFixture.dailyRoutine().id(1L).content("InMember").theme(theme).build(),
				DailyRoutineFixture.dailyRoutine().id(2L).content("NotInMember").theme(theme).build()
		);
		doReturn(routines).when(dailyRoutineRepository).findAllByTheme(theme);

		DailyRoutine routine = routines.get(0);
		MemberDailyRoutine memberRoutine = MemberDailyRoutineFixture.memberRoutine().member(member).routine(routine).build();
		member.getDailyRoutines().add(memberRoutine);

		// when
		final DailyRoutinesByThemeResponse actual = dailyRoutineService.getRoutinesByTheme(memberId, themeId);

		// then
		List<Long> routineIds = actual.routines().stream().map(DailyRoutineResponse::routineId).toList();
		assertThat(routineIds).containsExactlyInAnyOrder(2L);
	}

	private Member member(long memberId) {
		Member member = MemberFixture.member().id(memberId).build();
		doReturn(Optional.of(member)).when(memberRepository).findById(memberId);
		return member;
	}

	private DailyTheme dailyTheme(long themeId) {
		DailyTheme theme = DailyThemeFixture.dailyTheme().id(themeId).imageUrl("https://www...").build();
		doReturn(Optional.of(theme)).when(dailyThemeRepository).findById(themeId);
		return theme;
	}
}