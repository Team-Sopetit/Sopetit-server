package com.soptie.server.routine.service.integration;

import static com.soptie.server.routine.entity.RoutineType.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.repository.MemberRoutineRepository;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.Challenge;
import com.soptie.server.routine.repository.ChallengeRepository;
import com.soptie.server.routine.repository.RoutineRepository;
import com.soptie.server.routine.service.RoutineService;
import com.soptie.server.routine.service.dto.request.DailyRoutineListByThemeGetServiceRequest;
import com.soptie.server.routine.service.dto.request.DailyRoutineListByThemesGetServiceRequest;
import com.soptie.server.routine.service.dto.request.HappinessRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.request.HappinessSubRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.response.DailyRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.DailyRoutineListGetServiceResponse.DailyRoutineServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessRoutineListGetServiceResponse.HappinessRoutineServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessSubRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessSubRoutineListGetServiceResponse.HappinessSubRoutineServiceResponse;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.ChallengeFixture;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.fixture.MemberRoutineFixture;
import com.soptie.server.support.fixture.RoutineFixture;
import com.soptie.server.support.fixture.ThemeFixture;
import com.soptie.server.theme.entity.Theme;
import com.soptie.server.theme.repository.ThemeRepository;

@IntegrationTest
@Transactional
public class RoutineServiceIntegrationTest {
	
	@Autowired
	RoutineService routineService;

	@Autowired
	RoutineRepository routineRepository;

	@Autowired
	ThemeRepository themeRepository;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberRoutineRepository memberRoutineRepository;

	@Autowired
	ChallengeRepository challengeRepository;

	@Nested
	class getDailyRoutinesByTheme {
		
		Routine routine1, routine2, routine3;
		Theme theme1, theme2, theme3;
		
		@BeforeEach
		void setUp() {
			theme1 = themeRepository.save(ThemeFixture.theme().name("관계 쌓기").build());
			theme2 = themeRepository.save(ThemeFixture.theme().name("한 걸음 성장").build());
			theme3 = themeRepository.save(ThemeFixture.theme().name("새로운 나").build());

			routine1 = routineRepository.save(RoutineFixture.routine().type(DAILY).content("관계를 쌓아보자").theme(theme1).build());
			routine2 = routineRepository.save(RoutineFixture.routine().type(DAILY).content("성장하자").theme(theme2).build());
			routine3 = routineRepository.save(RoutineFixture.routine().type(DAILY).content("보여줄게 완전히 달라진 나").theme(theme3).build());
		}

		@Test
		@DisplayName("[성공] 테마 id 목록에 포함된 테마를 갖는 데일리 루틴 목록을 조회한다.")
		void getDailyRoutinesByThemeIds() {
			// given
			List<Long> themeIds = List.of(theme1.getId(), theme2.getId());
			DailyRoutineListByThemesGetServiceRequest request = DailyRoutineListByThemesGetServiceRequest.of(themeIds);

			// when
			final DailyRoutineListGetServiceResponse actual = routineService.getRoutinesByThemes(request);

			// then
			assertThat(actual.routines()).hasSize(2);
			List<Long> routineIds = actual.routines().stream().map(DailyRoutineServiceResponse::routineId).toList();
			assertThat(routineIds).containsExactlyInAnyOrder(routine1.getId(), routine2.getId());
		}
	}

	@Nested
	class getDailyRoutinesByThemes {

		Member member;
		Theme theme;
		Routine routine1, routine2, routineNoTheme, routineMemberHas, challengeRoutine;

		@BeforeEach
		void setUp() {
			member = memberRepository.save(MemberFixture.member().build());
			theme = themeRepository.save(ThemeFixture.theme().name("관계 쌓기").build());

			routine1 = routineRepository.save(RoutineFixture.routine().type(DAILY).content("관계 쌓자").theme(theme).build());
			routine2 = routineRepository.save(RoutineFixture.routine().type(DAILY).content("쌓자 관계").theme(theme).build());
			routineNoTheme = routineRepository.save(RoutineFixture.routine().type(DAILY).content("테마 없음").build());
			routineMemberHas = routineRepository.save(RoutineFixture.routine().type(DAILY).content("쌓자 관계").theme(theme).build());
			challengeRoutine = routineRepository.save(RoutineFixture.routine().type(CHALLENGE).content("관계 도전").theme(theme).build());

			memberRoutineRepository.save(
					MemberRoutineFixture.memberRoutine().type(DAILY).routineId(routineMemberHas.getId()).member(member).build());
		}

		@Test
		@DisplayName("[성공] 회원에게 없으면서, 주어진 테마에 속하는 데일리 루틴 목록을 조회한다.")
		void getDailyRoutinesByThemeMemberNotHas() {
			// given
			DailyRoutineListByThemeGetServiceRequest request = DailyRoutineListByThemeGetServiceRequest.of(member.getId(), theme.getId());

			// when
			final DailyRoutineListGetServiceResponse actual = routineService.getRoutinesByTheme(request);

			// then
			List<Long> routineIds = actual.routines().stream().map(DailyRoutineServiceResponse::routineId).toList();
			assertThat(routineIds).containsExactlyInAnyOrder(routine1.getId(), routine2.getId());
		}
	}

	@Nested
	class getHappinessRoutines {

		Routine routine1, routine2, routine3;
		Theme theme1, theme2;

		@BeforeEach
		void setUp() {
			theme1 = themeRepository.save(ThemeFixture.theme().name("관계 쌓기").color("라일락").build());
			theme2 = themeRepository.save(ThemeFixture.theme().name("한 걸음 성장").color("민트").build());

			routine1 = routineRepository.save(RoutineFixture.routine().type(CHALLENGE).content("관계쌓는").theme(theme1).build());
			routine2 = routineRepository.save(RoutineFixture.routine().type(CHALLENGE).content("성장하는").theme(theme1).build());
			routine3 = routineRepository.save(RoutineFixture.routine().type(CHALLENGE).content("보여주는").theme(theme2).build());
		}

		@Test
		@DisplayName("[성공] 테마에 포함된 행복 루틴 목록을 조회한다.")
		void getHappinessRoutinesByTheme() {
			// given
			HappinessRoutineListGetServiceRequest request = HappinessRoutineListGetServiceRequest.of(theme1.getId());

			// when
			final HappinessRoutineListGetServiceResponse actual = routineService.getHappinessRoutinesByTheme(request);

			// then
			assertThat(actual.routines()).hasSize(2);
			List<Long> routineIds = actual.routines().stream().map(HappinessRoutineServiceResponse::routineId).toList();
			assertThat(routineIds).containsExactlyInAnyOrder(routine1.getId(), routine2.getId());
		}
	}

	@Nested
	class getHappinessSubRoutines {

		Challenge challenge1, challenge2, challenge3;
		Routine routine1, routine2;
		Theme theme;

		@BeforeEach
		void setUp() {
			theme = themeRepository.save(ThemeFixture.theme().name("관계 쌓기").color("라일락").build());

			routine1 = routineRepository.save(RoutineFixture.routine().type(CHALLENGE).content("관계쌓는").theme(theme).build());
			routine2 = routineRepository.save(RoutineFixture.routine().type(CHALLENGE).content("성장하는").theme(theme).build());

			challenge1 = challengeRepository.save(ChallengeFixture.challenge().routine(routine1).build());
			challenge2 = challengeRepository.save(ChallengeFixture.challenge().routine(routine1).build());
			challenge3 = challengeRepository.save(ChallengeFixture.challenge().routine(routine2).build());
		}

		@Test
		@DisplayName("[성공] 행복 루틴에 포함된 서브 루틴 목록을 조회한다.")
		void getHappinessSubRoutinesByRoutine() {
			// given
			HappinessSubRoutineListGetServiceRequest request = HappinessSubRoutineListGetServiceRequest.of(routine1.getId());

			// when
			final HappinessSubRoutineListGetServiceResponse actual = routineService.getHappinessSubRoutines(request);

			// then
			assertThat(actual.challenges()).hasSize(2);

			List<Long> challengeIds = actual.challenges().stream()
					.map(HappinessSubRoutineServiceResponse::challengeId).toList();
			assertThat(challengeIds).containsExactlyInAnyOrder(challenge1.getId(), challenge2.getId());
		}
	}
}