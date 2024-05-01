package com.soptie.server.memberRoutine.service;

import static com.soptie.server.routine.message.RoutineErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.controller.v1.dto.request.MemberDailyRoutineCreateRequest;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.repository.CompletedMemberDailyRoutineRepository;
import com.soptie.server.memberRoutine.repository.MemberDailyRoutineRepository;
import com.soptie.server.memberRoutine.service.daily.MemberDailyRoutineServiceImpl;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineAchieveServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineCreateServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponse.MemberDailyRoutineServiceResponse;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.support.fixture.DailyRoutineFixture;
import com.soptie.server.support.fixture.DailyThemeFixture;
import com.soptie.server.support.fixture.MemberDailyRoutineFixture;
import com.soptie.server.support.fixture.MemberFixture;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
class MemberDailyRoutineServiceImplTest {

	@InjectMocks
	private MemberDailyRoutineServiceImpl memberDailyRoutineService;

	@Mock
	private MemberDailyRoutineRepository memberDailyRoutineRepository;
	@Mock
	private MemberRepository memberRepository;
	@Mock
	private DailyRoutineRepository dailyRoutineRepository;
	@Mock
	private CompletedMemberDailyRoutineRepository completedMemberDailyRoutineRepository;

	@Test
	void 회원이_데일리루틴을_추가하면_회원의_데일리루틴이_생성된다() {
		// given
		long memberId = 1L;
		Member member = member(memberId);

		long routineId = 2L;
		DailyRoutine dailyRoutine = dailyRoutine(routineId);

		doReturn(Optional.empty())
				.when(completedMemberDailyRoutineRepository).findByMemberAndRoutine(member, dailyRoutine);

		MemberDailyRoutine memberDailyRoutine = MemberDailyRoutineFixture
				.memberRoutine().id(3L).member(member).routine(dailyRoutine).build();

		doReturn(memberDailyRoutine).when(memberDailyRoutineRepository).save(any(MemberDailyRoutine.class));

		// when
		final MemberDailyRoutineCreateRequest request = new MemberDailyRoutineCreateRequest(routineId);
		final MemberDailyRoutineCreateServiceResponse actual = memberDailyRoutineService
				.createMemberDailyRoutine(MemberDailyRoutineCreateServiceRequest.of(memberId, request));

		// then
		assertThat(actual.routineId()).isEqualTo(memberDailyRoutine.getId());
	}

	@Test
	void 회원이_추가했던_데일리루틴을_추가하면_예외가_발생한다() {
		// given
		long memberId = 1L;
		Member member = member(memberId);

		long routineId = 2L;
		DailyRoutine dailyRoutine = dailyRoutine(routineId);

		MemberDailyRoutine memberDailyRoutine = MemberDailyRoutineFixture
				.memberRoutine().id(3L).member(member).routine(dailyRoutine).build();
		member.getDailyRoutines().add(memberDailyRoutine);

		// when & then
		final MemberDailyRoutineCreateRequest request = new MemberDailyRoutineCreateRequest(routineId);
		assertThatThrownBy(() -> memberDailyRoutineService
				.createMemberDailyRoutine(MemberDailyRoutineCreateServiceRequest.of(memberId, request)))
				.isInstanceOf(RoutineException.class)
				.hasMessage("[RoutineException] : " + DUPLICATED_ROUTINE.getMessage());
	}

	@Test
	void 회원이_추가했던_데일리루틴의_개수가_3개_이상이면_예외가_발생한다() {
		// given
		long memberId = 1L;
		Member member = member(memberId);

		member.getDailyRoutines().add(MemberDailyRoutineFixture.memberRoutine().build());
		member.getDailyRoutines().add(MemberDailyRoutineFixture.memberRoutine().build());
		member.getDailyRoutines().add(MemberDailyRoutineFixture.memberRoutine().build());

		// when & then
		final MemberDailyRoutineCreateRequest request = new MemberDailyRoutineCreateRequest(2L);
		assertThatThrownBy(() -> memberDailyRoutineService
				.createMemberDailyRoutine(MemberDailyRoutineCreateServiceRequest.of(memberId, request)))
				.isInstanceOf(RoutineException.class)
				.hasMessage("[RoutineException] : " + CANNOT_ADD_MEMBER_ROUTINE.getMessage());
	}

	@Test
	void 회원이_추가한_전체_데일리_루틴을_조회한다() {
		// given
		long memberId = 1L;
		Member member = member(memberId);

		DailyTheme theme = DailyThemeFixture.dailyTheme().id(100L).name("테마").imageUrl("url").build();

		DailyRoutine routine1 = DailyRoutineFixture.dailyRoutine().id(10L).theme(theme).content("루틴1").build();
		DailyRoutine routine2 = DailyRoutineFixture.dailyRoutine().id(20L).theme(theme).content("루틴2").build();
		DailyRoutine routine3 = DailyRoutineFixture.dailyRoutine().id(30L).theme(theme).content("루틴3").build();

		List<MemberDailyRoutine> memberRoutines = List.of(
				MemberDailyRoutineFixture.memberRoutine().id(1L).member(member).routine(routine1).build(),
				MemberDailyRoutineFixture.memberRoutine().id(2L).member(member).routine(routine2).build(),
				MemberDailyRoutineFixture.memberRoutine().id(3L).member(member).routine(routine3).build()
		);

		doReturn(memberRoutines).when(memberDailyRoutineRepository).findAllByMember(member);

		// when
		final MemberDailyRoutineListGetServiceResponse actual = memberDailyRoutineService
				.getMemberDailyRoutines(MemberDailyRoutineListGetServiceRequest.of(memberId));

		// then
		List<Long> memberRoutineIds = actual.routines().stream().map(MemberDailyRoutineServiceResponse::routineId).toList();
		assertThat(memberRoutineIds).containsExactlyInAnyOrder(1L, 2L, 3L);
	}

	@Test
	void 회원이_데일리_루틴을_달성하면_회원의_루틴_달성_정보가_업데이트된다() {
		// given
		long memberId = 1L;
		int dailyCottonCount = 0;
		Member member = member(memberId, dailyCottonCount);

		DailyRoutine routine = DailyRoutineFixture.dailyRoutine().id(2L).build();

		long memberRoutineId = 3L;
		int achieveCount = 0;
		MemberDailyRoutine memberDailyRoutine = memberDailyRoutine(memberRoutineId, achieveCount, member, routine);
		member.getDailyRoutines().add(memberDailyRoutine);

		// when
		final MemberDailyRoutineAchieveServiceResponse actual = memberDailyRoutineService
				.achieveMemberDailyRoutine(MemberDailyRoutineAchieveServiceRequest.of(memberId, memberRoutineId));

		// then
		MemberDailyRoutine memberRoutine = member.getDailyRoutines().get(0);
		assertThat(memberRoutine.isAchieve()).isTrue();
		assertThat(memberRoutine.getAchieveCount()).isEqualTo(achieveCount + 1);
		assertThat(member.getCottonInfo().getDailyCottonCount()).isEqualTo(dailyCottonCount + 1);

		assertThat(actual.routineId()).isEqualTo(memberRoutineId);
		assertThat(actual.isAchieve()).isTrue();
		assertThat(actual.achieveCount()).isEqualTo(achieveCount + 1);
	}

	private Member member(long id) {
		Member member = MemberFixture.member().id(id).build();
		doReturn(Optional.of(member)).when(memberRepository).findById(id);
		return member;
	}

	private Member member(long id, int dailyCottonCount) {
		Member member = MemberFixture.member().id(id).dailyCotton(dailyCottonCount).build();
		doReturn(Optional.of(member)).when(memberRepository).findById(id);
		return member;
	}

	private DailyRoutine dailyRoutine(long id) {
		DailyRoutine dailyRoutine = DailyRoutineFixture.dailyRoutine().id(id).build();
		doReturn(Optional.of(dailyRoutine)).when(dailyRoutineRepository).findById(id);
		return dailyRoutine;
	}

	private MemberDailyRoutine memberDailyRoutine(long id, int achieveCount, Member member, DailyRoutine routine) {
		MemberDailyRoutine memberRoutine = MemberDailyRoutineFixture.memberRoutine()
				.id(id).member(member).routine(routine).achieveCount(achieveCount).build();
		doReturn(Optional.of(memberRoutine)).when(memberDailyRoutineRepository).findById(id);
		return memberRoutine;
	}

}