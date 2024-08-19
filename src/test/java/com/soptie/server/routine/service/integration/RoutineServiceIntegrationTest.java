package com.soptie.server.routine.service.integration;

import static com.soptie.server.persistence.entity.RoutineType.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.repository.MemberRepository;
import com.soptie.server.persistence.entity.MemberRoutine;
import com.soptie.server.persistence.repository.MemberRoutineRepository;
import com.soptie.server.persistence.entity.Challenge;
import com.soptie.server.persistence.entity.Routine;
import com.soptie.server.persistence.entity.RoutineType;
import com.soptie.server.persistence.repository.ChallengeRepository;
import com.soptie.server.persistence.repository.RoutineRepository;
import com.soptie.server.domain.routine.RoutineService;
import com.soptie.server.domain.routine.HappinessSubRoutineListGetServiceRequest;
import com.soptie.server.domain.routine.ChallengeRoutineListAcquireServiceResponse;
import com.soptie.server.domain.routine.HappinessSubRoutineListGetServiceResponse;
import com.soptie.server.domain.routine.HappinessSubRoutineListGetServiceResponse.HappinessSubRoutineServiceResponse;
import com.soptie.server.domain.routine.RoutineVO;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.ChallengeFixture;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.fixture.MemberRoutineFixture;
import com.soptie.server.support.fixture.RoutineFixture;
import com.soptie.server.support.fixture.ThemeFixture;
import com.soptie.server.persistence.entity.Theme;
import com.soptie.server.persistence.repository.ThemeRepository;

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
	class DailyRoutine {

		@Nested
		class Acquire {

			Theme theme1;
			Theme theme2;
			Theme theme3;

			Routine routineOfTheme1;
			Routine routineOfTheme2;
			Routine routineOfTheme3;

			@BeforeEach
			void setUp() {
				theme1 = themeRepository.save(ThemeFixture.theme().name("관계 쌓기").build());
				theme2 = themeRepository.save(ThemeFixture.theme().name("한 걸음 성장").build());
				theme3 = themeRepository.save(ThemeFixture.theme().name("새로운 나").build());

				routineOfTheme1 = routineRepository.save(
					RoutineFixture.routine().type(RoutineType.DAILY).content("관계를 쌓아보자").theme(theme1).build());
				routineOfTheme2 = routineRepository.save(
					RoutineFixture.routine().type(RoutineType.DAILY).content("성장하자").theme(theme2).build());
				routineOfTheme3 = routineRepository.save(
					RoutineFixture.routine().type(RoutineType.DAILY).content("완전히 달라진 나").theme(theme3).build());
			}

			@Test
			@DisplayName("[성공] 테마 id 목록에 포함된 테마를 갖는 데일리 루틴 목록을 조회한다.")
			void acquireAllByThemeIds() {
				// given
				List<Long> themeIds = List.of(theme1.getId(), theme2.getId());

				// when
				final List<RoutineVO> actual = routineService.acquireAllInDailyByThemeIds(themeIds);

				// then
				Assertions.assertThat(actual).hasSize(2);
				List<Long> routineIds = actual.stream().map(RoutineVO::routineId).toList();
				Assertions.assertThat(routineIds)
					.containsExactlyInAnyOrder(routineOfTheme1.getId(), routineOfTheme2.getId());
			}

			@Test
			@DisplayName("[성공] 각 테마 id 별로 데일리 루틴 목록을 조회한다.")
			void acquireAllWithThemeIds() {
				// given
				Set<Long> themeIds = new LinkedHashSet<>();
				themeIds.add(theme2.getId());
				themeIds.add(theme1.getId());

				// when
				final Map<Long, List<Routine>> actual = routineService.acquireAllInDailyWithThemeId(themeIds);

				// then
				Assertions.assertThat(actual.keySet()).containsExactly(theme2.getId(), theme1.getId());

				List<Long> routineIdsForTheme1 = actual.get(theme1.getId()).stream().map(Routine::getId).toList();
				Assertions.assertThat(routineIdsForTheme1).containsExactlyInAnyOrder(routineOfTheme1.getId());

				List<Long> routineIdsForTheme2 = actual.get(theme2.getId()).stream().map(Routine::getId).toList();
				Assertions.assertThat(routineIdsForTheme2).containsExactlyInAnyOrder(routineOfTheme2.getId());
			}

			@Test
			@DisplayName("[성공] 회원에게 없으면서, 주어진 테마에 속하는 데일리 루틴 목록을 조회한다.")
			void acquireAllNotInMemberByThemeId() {
				// given
				Member member = memberRepository.save(MemberFixture.member().build());

				Routine routineNotInMember1 = routineRepository.save(RoutineFixture.routine()
					.type(RoutineType.DAILY).content("조회될 루틴1").theme(theme1).build());
				Routine routineNotInMember2 = routineRepository.save(RoutineFixture.routine()
					.type(RoutineType.DAILY).content("조회될 루틴2").theme(theme1).build());

				memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
					.type(RoutineType.DAILY).routineId(routineOfTheme1.getId()).member(member).build());

				// when
				final List<RoutineVO> actual = routineService
					.acquireAllInDailyNotInMemberByThemeId(member.getId(), theme1.getId());

				// then
				List<Long> routineIds = actual.stream().map(RoutineVO::routineId).toList();
				Assertions.assertThat(routineIds)
					.containsExactlyInAnyOrder(routineNotInMember1.getId(), routineNotInMember2.getId());
			}
		}
	}

	@Nested
	class AcquireHappinessRoutine {

		Routine routine1;
		Routine routine2;
		Routine routine3;
		Theme theme1;
		Theme theme2;

		@BeforeEach
		void setUp() {
			theme1 = themeRepository.save(ThemeFixture.theme().name("관계 쌓기").color("라일락").build());
			theme2 = themeRepository.save(ThemeFixture.theme().name("한 걸음 성장").color("민트").build());

			routine1 = routineRepository.save(
				RoutineFixture.routine().type(CHALLENGE).content("관계쌓는").theme(theme1).build());
			routine2 = routineRepository.save(
				RoutineFixture.routine().type(CHALLENGE).content("성장하는").theme(theme1).build());
			routine3 = routineRepository.save(
				RoutineFixture.routine().type(CHALLENGE).content("보여주는").theme(theme2).build());
		}

		@Test
		@DisplayName("[성공] 테마에 포함된 행복 루틴 목록을 조회한다.")
		void getHappinessRoutinesByTheme() {
			// when
			final List<Routine> actual = routineService.acquireAllInHappinessByThemeId(theme1.getId());

			// then
			Assertions.assertThat(actual).hasSize(2);
			List<Long> routineIds = actual.stream().map(Routine::getId).toList();
			Assertions.assertThat(routineIds).containsExactlyInAnyOrder(routine1.getId(), routine2.getId());
		}
	}

	@Nested
	class AcquireSubHappinessRoutine {

		Challenge challenge1;
		Challenge challenge2;
		Challenge challenge3;
		Routine routine1;
		Routine routine2;
		Theme theme;

		@BeforeEach
		void setUp() {
			theme = themeRepository.save(ThemeFixture.theme().name("관계 쌓기").color("라일락").build());

			routine1 = routineRepository.save(
				RoutineFixture.routine().type(CHALLENGE).content("관계쌓는").theme(theme).build());
			routine2 = routineRepository.save(
				RoutineFixture.routine().type(CHALLENGE).content("성장하는").theme(theme).build());

			challenge1 = challengeRepository.save(
				ChallengeFixture.challenge()
					.content("도전 루틴 내용")
					.description("도전 루틴 설명")
					.requiredTime("10분")
					.place("소프티 숙소")
					.routine(routine1)
					.build()
			);
			challenge2 = challengeRepository.save(
				ChallengeFixture.challenge()
					.content("도전 루틴 내용")
					.description("도전 루틴 설명")
					.requiredTime("10분")
					.place("소프티 숙소")
					.routine(routine1)
					.build()
			);
			challenge3 = challengeRepository.save(
				ChallengeFixture.challenge()
					.content("도전 루틴 내용")
					.description("도전 루틴 설명")
					.requiredTime("10분")
					.place("소프티 숙소")
					.routine(routine2)
					.build()
			);
		}

		@Test
		@DisplayName("[성공] 행복 루틴에 포함된 서브 루틴 목록을 조회한다.")
		void getHappinessSubRoutinesByRoutine() {
			// given
			HappinessSubRoutineListGetServiceRequest request = HappinessSubRoutineListGetServiceRequest.of(
				routine1.getId());

			// when
			final HappinessSubRoutineListGetServiceResponse actual = routineService.getHappinessSubRoutines(request);

			// then
			Assertions.assertThat(actual.challenges()).hasSize(2);

			List<Long> challengeIds = actual.challenges().stream()
				.map(HappinessSubRoutineServiceResponse::challengeId).toList();
			Assertions.assertThat(challengeIds).containsExactlyInAnyOrder(challenge1.getId(), challenge2.getId());
		}
	}

	@Nested
	class AcquireChallengeByThemes {

		Member member;
		Challenge challenge1;
		Challenge challenge2;
		Challenge challenge3;
		Routine routine1;
		Routine routine2;
		Theme theme;
		MemberRoutine memberRoutine;

		@BeforeEach
		void setUp() {
			member = memberRepository.save(MemberFixture.member().build());
			theme = themeRepository.save(ThemeFixture.theme().name("관계 쌓기").color("라일락").build());

			routine1 = routineRepository.save(
				RoutineFixture.routine().type(CHALLENGE).content("관계쌓는").theme(theme).build());
			routine2 = routineRepository.save(
				RoutineFixture.routine().type(CHALLENGE).content("성장하는").theme(theme).build());

			challenge1 = challengeRepository.save(
				ChallengeFixture.challenge().routine(routine1)
					.content("선배 마라탕 사주세요.")
					.description("혹시 탕후루도 같이?")
					.requiredTime("30분")
					.place("소프티 숙소")
					.build()
			);
			challenge2 = challengeRepository.save(
				ChallengeFixture.challenge().routine(routine1)
					.content("선배 탕후루 사주세요.")
					.description("혹시 마라탕도 같이?")
					.requiredTime("1시간")
					.place("소프티 숙소")
					.build()
			);
			challenge3 = challengeRepository.save(
				ChallengeFixture.challenge().routine(routine2)
					.content("선배")
					.description("안된다고요?")
					.requiredTime("10분")
					.place("소프티 숙소")
					.build()
			);

			memberRoutine = memberRoutineRepository.save(
				MemberRoutineFixture.memberRoutine()
					.type(CHALLENGE).routineId(challenge2.getId()).member(member).build()
			);
		}

		@Test
		@DisplayName("[성공] 테마에 속한 도전 루틴 목록을 조회한다. 이 때, 멤버가 가지고 있는 도전 루틴이라면 hasRoutine의 값이 true이다.")
		void acquireAllByTheme() {
			// given
			long themeId = theme.getId();
			long memberId = member.getId();

			// when
			final Map<String, ChallengeRoutineListAcquireServiceResponse> actual =
				routineService.acquireAllInChallengeWithThemeId(memberId, themeId);

			// then
			Assertions.assertThat(actual.keySet())
				.containsExactlyInAnyOrder(routine1.getContent(), routine2.getContent());

			ChallengeRoutineListAcquireServiceResponse challenges = actual.get(routine1.getContent());
			Assertions.assertThat(
				challenges.challenges().stream()
					.filter(challenge -> challenge.challenge().challengeId() == challenge1.getId())
					.findAny()
					.orElseThrow()
					.hasRoutine()
			).isEqualTo(false);
			Assertions.assertThat(
				challenges.challenges().stream()
					.filter(challenge -> challenge.challenge().challengeId() == challenge2.getId())
					.findAny()
					.orElseThrow()
					.hasRoutine()
			).isEqualTo(true);
		}
	}
}
