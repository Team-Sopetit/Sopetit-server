package com.soptie.server.domain.memberroutine;

import static com.soptie.server.common.message.RoutineErrorCode.*;
import static com.soptie.server.persistence.entity.RoutineType.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.request.memberroutine.MemberDailyRoutineCreateRequest;
import com.soptie.server.api.controller.dto.request.memberroutine.MemberHappinessRoutineRequest;
import com.soptie.server.common.exception.RoutineException;
import com.soptie.server.domain.memberroutine.MemberDailyRoutinesAcquireServiceResponse.MemberDailyRoutineServiceResponse;
import com.soptie.server.persistence.entity.Challenge;
import com.soptie.server.persistence.entity.DeletedMemberRoutine;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.entity.MemberRoutine;
import com.soptie.server.persistence.entity.Routine;
import com.soptie.server.persistence.entity.Theme;
import com.soptie.server.persistence.repository.ChallengeRepository;
import com.soptie.server.persistence.repository.DeletedMemberRoutineRepository;
import com.soptie.server.persistence.repository.MemberRepository;
import com.soptie.server.persistence.repository.MemberRoutineRepository;
import com.soptie.server.persistence.repository.RoutineRepository;
import com.soptie.server.persistence.repository.ThemeRepository;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.ChallengeFixture;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.fixture.MemberRoutineFixture;
import com.soptie.server.support.fixture.RoutineFixture;
import com.soptie.server.support.fixture.ThemeFixture;

@IntegrationTest
@Transactional
public class MemberRoutineServiceIntegrationTest {

	@Autowired
	MemberRoutineCreateService memberRoutineCreateService;

	@Autowired
	MemberRoutineReadService memberRoutineReadService;

	@Autowired
	MemberRoutineDeleteService memberRoutineDeleteService;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	RoutineRepository routineRepository;

	@Autowired
	MemberRoutineRepository memberRoutineRepository;

	@Autowired
	DeletedMemberRoutineRepository deletedMemberRoutineRepository;

	@Autowired
	ThemeRepository themeRepository;

	@Autowired
	ChallengeRepository challengeRepository;

	@Nested
	class Add {

		Member member;
		Routine routine;
		Challenge challenge;

		@BeforeEach
		void setUp() {
			member = memberRepository.save(MemberFixture.member().build());
			routine = routineRepository.save(RoutineFixture.routine().build());
			challenge = challengeRepository.save(ChallengeFixture.challenge().build());
		}

		@Test
		@DisplayName("[성공] 삭제한 적 없는 데일리 루틴을 추가한다.")
		void createHasNotDeletedDailyRoutine() {
			// given
			MemberDailyRoutineCreateServiceRequest request = MemberDailyRoutineCreateServiceRequest.of(member.getId(),
				new MemberDailyRoutineCreateRequest(routine.getId()));

			// when
			memberRoutineCreateService.createDailyRoutine(request);

			// then
			assertThat(memberRoutineRepository.existsByMemberAndTypeAndRoutineId(member, routine.getType(),
				routine.getId())).isTrue();
		}

		@Test
		@DisplayName("[성공] 삭제한 적 있는 데일리 루틴을 추가한디.")
		void createHasDeletedDailyRoutine() {
			// given
			MemberRoutine memberRoutine = MemberRoutineFixture.memberRoutine()
				.isAchieve(true)
				.achieveCount(5)
				.type(routine.getType())
				.routineId(routine.getId())
				.member(member)
				.build();

			saveAndDelete(memberRoutine);

			MemberDailyRoutineCreateServiceRequest request = MemberDailyRoutineCreateServiceRequest.of(member.getId(),
				new MemberDailyRoutineCreateRequest(routine.getId()));

			// when
			memberRoutineCreateService.createDailyRoutine(request);

			// then
			final MemberRoutine found = memberRoutineRepository.findByMemberAndTypeAndRoutineId(member,
				routine.getType(), routine.getId()).orElseThrow(RuntimeException::new);

			assertThat(found.isAchieve()).isTrue();
			assertThat(found.getAchieveCount()).isEqualTo(memberRoutine.getAchieveCount());
		}

		@Test
		@DisplayName("[예외] 가지고 있는 데일리 루틴은 추가할 수 없다.")
		void cannotCreateDailyRoutineIfAlreadyHave() {
			// given
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.type(routine.getType())
				.routineId(routine.getId())
				.member(member)
				.build());

			MemberDailyRoutineCreateServiceRequest request = MemberDailyRoutineCreateServiceRequest.of(member.getId(),
				new MemberDailyRoutineCreateRequest(routine.getId()));

			// when & then
			assertThatThrownBy(() -> memberRoutineCreateService.createDailyRoutine(request)).isInstanceOf(
				RoutineException.class).hasMessage("[RoutineException] : " + DUPLICATED_ROUTINE.getMessage());
		}

		@Test
		@DisplayName("[성공] 삭제한 적 없는 행복 루틴을 추가한다.")
		void createHasNotDeletedHappinessRoutine() {
			// given
			MemberHappinessRoutineCreateServiceRequest request = MemberHappinessRoutineCreateServiceRequest.of(
				member.getId(), new MemberHappinessRoutineRequest(challenge.getId()));

			// when
			memberRoutineCreateService.createHappinessRoutine(request);

			// then
			assertThat(memberRoutineRepository.existsByMemberAndTypeAndRoutineId(member, CHALLENGE,
				challenge.getId())).isTrue();
		}

		@Test
		@DisplayName("[성공] 삭제한 적 있는 행복 루틴을 추가한디.")
		void createHasDeletedHappinessRoutine() {
			// given
			MemberRoutine memberRoutine = MemberRoutineFixture.memberRoutine()
				.isAchieve(true)
				.achieveCount(5)
				.type(CHALLENGE)
				.routineId(challenge.getId())
				.member(member)
				.build();

			saveAndDelete(memberRoutine);

			MemberHappinessRoutineCreateServiceRequest request = MemberHappinessRoutineCreateServiceRequest.of(
				member.getId(), new MemberHappinessRoutineRequest(challenge.getId()));

			// when
			memberRoutineCreateService.createHappinessRoutine(request);

			// then
			final MemberRoutine found = memberRoutineRepository.findByMemberAndTypeAndRoutineId(member, CHALLENGE,
				challenge.getId()).orElseThrow(RuntimeException::new);

			assertThat(found.isAchieve()).isTrue();
			assertThat(found.getAchieveCount()).isEqualTo(memberRoutine.getAchieveCount());
		}

		@Test
		@DisplayName("[예외] 도전 루틴은 최대 1개까지 가질 수 있다.")
		void cannotCreateChallengeRoutineIfAlreadyHaveOne() {
			// given
			Challenge challenge2 = challengeRepository.save(ChallengeFixture.challenge().build());
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.type(CHALLENGE)
				.routineId(challenge2.getId())
				.member(member)
				.build());

			MemberHappinessRoutineCreateServiceRequest request = MemberHappinessRoutineCreateServiceRequest.of(
				member.getId(), new MemberHappinessRoutineRequest(challenge.getId()));

			// when & then
			assertThatThrownBy(() -> memberRoutineCreateService.createHappinessRoutine(request)).isInstanceOf(
				RoutineException.class).hasMessage("[RoutineException] : " + CANNOT_ADD_MEMBER_ROUTINE.getMessage());
		}

		private void saveAndDelete(MemberRoutine memberRoutine) {
			MemberRoutine savedMemberRoutine = memberRoutineRepository.save(memberRoutine);
			deletedMemberRoutineRepository.save(new DeletedMemberRoutine(savedMemberRoutine));
			memberRoutineRepository.delete(savedMemberRoutine);
		}

	}

	@Nested
	class Delete {

		Member member;
		Routine routine;
		MemberRoutine memberRoutine;

		@BeforeEach
		void setUp() {
			member = memberRepository.save(MemberFixture.member().build());
			routine = routineRepository.save(RoutineFixture.routine().build());
			memberRoutine = memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member)
				.routineId(routine.getId())
				.type(routine.getType())
				.build());
		}

		@Test
		@DisplayName("[성공] 회원이 가진 데일리 루틴을 삭제한다.")
		void deleteMemberDailyRoutines() {
			// given
			MemberRoutinesDeleteServiceRequest request = MemberRoutinesDeleteServiceRequest.of(member.getId(),
				List.of(memberRoutine.getId()));

			// when
			memberRoutineDeleteService.deleteMemberRoutines(request);

			// then
			assertThat(memberRoutineRepository.existsByMemberAndTypeAndRoutineId(member, routine.getType(),
				routine.getId())).isFalse();
			assertThat(deletedMemberRoutineRepository.existsByMemberAndTypeAndRoutineId(member, routine.getType(),
				routine.getId())).isTrue();
		}
	}

	@Nested
	class Acquire {

		Member member1;
		Member member2;
		Theme theme1;
		Theme theme2;
		Routine routine1;
		Routine routine2;
		Routine routine3;
		Routine challengeRoutine;

		@BeforeEach
		void setUp() {
			member1 = memberRepository.save(MemberFixture.member().build());
			member2 = memberRepository.save(MemberFixture.member().build());

			theme1 = themeRepository.save(ThemeFixture.theme().name("테마 1").build());
			theme2 = themeRepository.save(ThemeFixture.theme().name("테마 2").build());

			routine1 = routineRepository.save(
				RoutineFixture.routine().theme(theme1).type(DAILY).content("새로운 나").build());
			routine2 = routineRepository.save(
				RoutineFixture.routine().theme(theme1).type(DAILY).content("깨끗한 나").build());
			routine3 = routineRepository.save(
				RoutineFixture.routine().theme(theme2).type(DAILY).content("똑똑한 나").build());
			challengeRoutine = routineRepository.save(
				RoutineFixture.routine().theme(theme1).type(CHALLENGE).content("도전 루틴").build());
		}

		@Test
		@DisplayName("[성공] 회원이 가진 모든 데일리 루틴을 조회한다.")
		void getMemberDailyRoutinesByMember() {
			// given
			MemberRoutine memberRoutine1 = memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member1)
				.routineId(routine1.getId())
				.type(routine1.getType())
				.build());
			MemberRoutine memberRoutine2 = memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member1)
				.routineId(routine2.getId())
				.type(routine2.getType())
				.build());
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member2)
				.routineId(routine3.getId())
				.type(routine3.getType())
				.build());
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member1)
				.routineId(challengeRoutine.getId())
				.type(challengeRoutine.getType())
				.build());

			MemberDailyRoutineListAcquireServiceRequest request = MemberDailyRoutineListAcquireServiceRequest.of(
				member1.getId());

			// when
			final MemberDailyRoutinesAcquireServiceResponse actual = memberRoutineReadService.getDailyRoutines(request);

			// then
			List<String> contents = actual.routines().stream().map(MemberDailyRoutineServiceResponse::content).toList();
			assertThat(contents).hasSize(2);
			assertThat(contents).containsExactlyInAnyOrder(routine1.getContent(), routine2.getContent());

			List<Long> memberRoutineIds = actual.routines()
				.stream()
				.map(MemberDailyRoutineServiceResponse::routineId)
				.toList();
			assertThat(memberRoutineIds).containsExactlyInAnyOrder(memberRoutine1.getRoutineId(),
				memberRoutine2.getId());
		}

		@Test
		@DisplayName("[성공] 회원이 가진 행복 루틴을 조회할 수 있다.")
		void getMemberHappinessRoutine() {
			// given
			Challenge challenge = challengeRepository.save(
				ChallengeFixture.challenge()
					.content("무한~ 도전~")
					.description("무한으로 즐겨요")
					.requiredTime("2시간")
					.place("MBC")
					.routine(challengeRoutine)
					.build());
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member1)
				.type(CHALLENGE)
				.routineId(challenge.getId())
				.build());

			MemberHappinessRoutineGetServiceRequest request = MemberHappinessRoutineGetServiceRequest.of(
				member1.getId());

			// when
			final Optional<MemberHappinessRoutineGetServiceResponse> actual = memberRoutineReadService
				.getHappinessRoutine(request);

			// then
			assertThat(actual).isPresent();

			final MemberHappinessRoutineGetServiceResponse response = actual.get();
			assertThat(response.content()).isEqualTo(challenge.getContent());
			assertThat(response.description()).isEqualTo(challenge.getDescription());
		}

		@Test
		@DisplayName("[성공] 회원이 가진 행복 루틴이 없으면 빈 값으로 조회된다.")
		void getEmptyWhenMemberHasNotHappinessRoutine() {
			// given
			MemberHappinessRoutineGetServiceRequest request = MemberHappinessRoutineGetServiceRequest.of(
				member1.getId());

			// when
			final Optional<MemberHappinessRoutineGetServiceResponse> actual = memberRoutineReadService
				.getHappinessRoutine(request);

			// then
			assertThat(actual).isEmpty();
		}

		@Test
		@DisplayName("[성공] 회원이 가진 모든 데일리 루틴을 테마별로 조회한다. 이 때, 루틴은 가나다순으로 정렬된다.")
		void acquireAllByMember() {
			// given
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member1).routineId(routine1.getId()).type(routine1.getType()).build());
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member1).routineId(routine2.getId()).type(routine2.getType()).build());
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member1).routineId(routine3.getId()).type(routine3.getType()).build());

			MemberDailyRoutineListAcquireServiceRequest request = MemberDailyRoutineListAcquireServiceRequest.of(
				member1.getId());

			// when
			final MemberDailyRoutineListAcquireServiceResponse actual = memberRoutineReadService.acquireAll(request);

			// then
			int themeCount = actual.routines().size();
			assertThat(themeCount).isEqualTo(2);
			List<String> contents = actual.routines().get(0).routines().stream().map(
				MemberDailyRoutinesAcquireServiceResponse.MemberDailyRoutineServiceResponse::content).toList();
			assertThat(contents).hasSize(2);
			assertThat(contents).containsExactly(routine2.getContent(), routine1.getContent());
		}

		@Test
		@DisplayName("[성공] 회원의 도전 루틴이 존재한다면 해당 도전 루틴을 테마와 함께 조회한다.")
		void acquireByMember() {
			// given
			Challenge challenge = challengeRepository.save(
				ChallengeFixture.challenge()
					.content("무한~ 도전~")
					.description("무한으로 즐겨요")
					.requiredTime("2시간")
					.place("MBC")
					.routine(challengeRoutine)
					.build());
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
				.member(member1).routineId(challenge.getId()).type(challengeRoutine.getType()).build());

			MemberChallengeRoutineAcquireServiceRequest request =
				MemberChallengeRoutineAcquireServiceRequest.of(member1.getId());

			// when
			final Optional<MemberChallengeRoutineAcquireServiceResponse> actual =
				memberRoutineReadService.acquire(request);

			// then
			assertThat(actual).isPresent();

			final MemberChallengeRoutineAcquireServiceResponse response = actual.get();
			assertThat(response.theme().themeId()).isEqualTo(challenge.getRoutine().getTheme().getId());
			assertThat(response.theme().name()).isEqualTo(challenge.getRoutine().getTheme().getName());
			assertThat(response.content()).isEqualTo(challenge.getContent());
		}

		@Test
		@DisplayName("[성공] 회원이 가진 도전 루틴이 없으면 빈 값으로 조회된다.")
		void acquireEmptyWhenMemberHasNotChallengeRoutine() {
			// given
			MemberChallengeRoutineAcquireServiceRequest request =
				MemberChallengeRoutineAcquireServiceRequest.of(member1.getId());

			// when
			final Optional<MemberChallengeRoutineAcquireServiceResponse> actual =
				memberRoutineReadService.acquire(request);

			// then
			assertThat(actual).isEmpty();
		}
	}
}
