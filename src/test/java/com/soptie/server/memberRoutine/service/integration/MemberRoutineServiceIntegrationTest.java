package com.soptie.server.memberRoutine.service.integration;

import static com.soptie.server.routine.entity.RoutineType.*;
import static com.soptie.server.routine.message.RoutineErrorCode.*;
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
import com.soptie.server.memberRoutine.controller.v1.dto.request.MemberDailyRoutineCreateRequest;
import com.soptie.server.memberRoutine.entity.DeletedMemberRoutine;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.memberRoutine.repository.DeletedMemberRoutineRepository;
import com.soptie.server.memberRoutine.repository.MemberRoutineRepository;
import com.soptie.server.memberRoutine.service.MemberRoutineService;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponse.MemberDailyRoutineServiceResponse;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.RoutineRepository;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.fixture.MemberRoutineFixture;
import com.soptie.server.support.fixture.RoutineFixture;
import com.soptie.server.support.fixture.ThemeFixture;
import com.soptie.server.theme.entity.Theme;
import com.soptie.server.theme.repository.ThemeRepository;

@IntegrationTest
@Transactional
public class MemberRoutineServiceIntegrationTest {

	@Autowired
	MemberRoutineService memberRoutineService;

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

	@Nested
	class createDailyRoutine {
		Member member;
		Routine routine;

		@BeforeEach
		void setUp() {
			member = memberRepository.save(MemberFixture.member().build());
			routine = routineRepository.save(RoutineFixture.routine().build());
		}

		@Test
		@DisplayName("[성공] 삭제한 적 없는 루틴을 추가한다.")
		void createHasNotDeleted() {
			// given
			MemberDailyRoutineCreateServiceRequest request = MemberDailyRoutineCreateServiceRequest.of(
					member.getId(),
					new MemberDailyRoutineCreateRequest(routine.getId())
			);

			// when
			memberRoutineService.createDailyRoutine(request);

			// then
			assertThat(memberRoutineRepository
					.existsByMemberAndTypeAndRoutineId(member, routine.getType(), routine.getId())).isTrue();
		}

		@Test
		@DisplayName("[성공] 삭제한 적 있는 루틴을 추가한디.")
		void createHasDeleted() {
			// given
			MemberRoutine memberRoutine = MemberRoutineFixture.memberRoutine()
					.isAchieve(true)
					.achieveCount(5)
					.type(routine.getType())
					.routineId(routine.getId())
					.member(member)
					.build();

			saveAndDelete(memberRoutine);

			MemberDailyRoutineCreateServiceRequest request = MemberDailyRoutineCreateServiceRequest.of(
					member.getId(),
					new MemberDailyRoutineCreateRequest(routine.getId())
			);

			// when
			memberRoutineService.createDailyRoutine(request);

			// then
			final MemberRoutine found = memberRoutineRepository
					.findByMemberAndTypeAndRoutineId(member, routine.getType(), routine.getId())
					.orElseThrow(RuntimeException::new);

			assertThat(found.isAchieve()).isTrue();
			assertThat(found.getAchieveCount()).isEqualTo(memberRoutine.getAchieveCount());
		}

		@Test
		@DisplayName("[예외] 가지고 있는 루틴은 추가할 수 없다.")
		void cannotCreateIfAlreadyHave() {
			// given
			memberRoutineRepository.save(MemberRoutineFixture
					.memberRoutine().type(routine.getType()).routineId(routine.getId()).member(member).build());

			MemberDailyRoutineCreateServiceRequest request = MemberDailyRoutineCreateServiceRequest.of(
					member.getId(),
					new MemberDailyRoutineCreateRequest(routine.getId())
			);

			// when & then
			assertThatThrownBy(() -> memberRoutineService.createDailyRoutine(request))
					.isInstanceOf(RoutineException.class)
					.hasMessage("[RoutineException] : " + DUPLICATED_ROUTINE.getMessage());
		}

		private void saveAndDelete(MemberRoutine memberRoutine) {
			MemberRoutine savedMemberRoutine = memberRoutineRepository.save(memberRoutine);
			deletedMemberRoutineRepository.save(new DeletedMemberRoutine(savedMemberRoutine));
			memberRoutineRepository.delete(savedMemberRoutine);
		}
	}

	@Nested
	class deleteDailyRoutines {
		Member member;
		Routine routine;
		MemberRoutine memberRoutine;

		@BeforeEach
		void setUp() {
			member = memberRepository.save(MemberFixture.member().build());
			routine = routineRepository.save(RoutineFixture.routine().build());
			memberRoutine = memberRoutineRepository.save(MemberRoutineFixture
					.memberRoutine().member(member).routineId(routine.getId()).type(routine.getType()).build());
		}

		@Test
		@DisplayName("[성공] 회원이 가진 데일리 루틴을 삭제한다.")
		void deleteMemberDailyRoutines() {
			// given
			MemberDailyRoutineDeleteServiceRequest request = MemberDailyRoutineDeleteServiceRequest.of(
					member.getId(),
					List.of(memberRoutine.getId())
			);

			// when
			memberRoutineService.deleteDailyRoutines(request);

			// then
			assertThat(memberRoutineRepository.existsByMemberAndTypeAndRoutineId(member, routine.getType(), routine.getId())).isFalse();
			assertThat(deletedMemberRoutineRepository.existsByMemberAndTypeAndRoutineId(member, routine.getType(), routine.getId())).isTrue();
		}
	}

	@Nested
	class getDailyRoutines {

		Member member1, member2;
		Theme theme;
		Routine routine1, routine2, routine3;

		@BeforeEach
		void setUp() {
			member1 = memberRepository.save(MemberFixture.member().build());
			member2 = memberRepository.save(MemberFixture.member().build());

			theme = themeRepository.save(ThemeFixture.theme().build());

			routine1 = routineRepository.save(RoutineFixture.routine().theme(theme).type(DAILY).content("새로운 나").build());
			routine2 = routineRepository.save(RoutineFixture.routine().theme(theme).type(DAILY).content("깨끗한 나").build());
			routine3 = routineRepository.save(RoutineFixture.routine().theme(theme).type(DAILY).content("똑똑한 나").build());
		}

		@Test
		@DisplayName("[성공] 회원이 가진 모든 데일리 루틴을 조회한다.")
		void getMemberDailyRoutinesByMember() {
			// given
			MemberRoutine memberRoutine1 = memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
					.member(member1).routineId(routine1.getId()).type(routine1.getType()).build());
			MemberRoutine memberRoutine2 = memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
					.member(member1).routineId(routine2.getId()).type(routine2.getType()).build());
			memberRoutineRepository.save(MemberRoutineFixture.memberRoutine()
					.member(member2).routineId(routine3.getId()).type(routine3.getType()).build());

			MemberDailyRoutineListGetServiceRequest request = MemberDailyRoutineListGetServiceRequest.of(member1.getId());

			// when
			final MemberDailyRoutineListGetServiceResponse actual = memberRoutineService.getDailyRoutines(request);

			// then
			List<String> contents = actual.routines().stream().map(MemberDailyRoutineServiceResponse::content).toList();
			assertThat(contents).hasSize(2);
			assertThat(contents).containsExactlyInAnyOrder(routine1.getContent(), routine2.getContent());

			List<Long> memberRoutineIds = actual.routines().stream().map(MemberDailyRoutineServiceResponse::routineId).toList();
			assertThat(memberRoutineIds).containsExactlyInAnyOrder(memberRoutine1.getRoutineId(), memberRoutine2.getId());
		}
	}
}