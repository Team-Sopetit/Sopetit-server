package com.soptie.server.memberRoutine.service;

import static com.soptie.server.routine.entity.RoutineType.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutineAchieveServiceRequest;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.fixture.MemberRoutineFixture;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class MemberRoutineServiceTest {

	@InjectMocks
	private MemberRoutineService memberRoutineService;

	@Mock
	private MemberFinder memberFinder;

	@Mock
	private MemberRoutineFinder memberRoutineFinder;

	@Test
	@DisplayName("[성공] 데일리 루틴을 달성하면 달성 횟수와 데일리 솜 뭉치 개수가 1만큼 증가한다.")
	void shouldUpdateAchieveCountAndCottonCountWhenAchieveDailyRoutine() {
		// given
		int beforeCottonCount = 0;
		int beforeAchieveCount = 0;

		Member member = MemberFixture.member().id(1L).dailyCotton(beforeCottonCount).build();
		MemberRoutine memberRoutine = MemberRoutineFixture.memberRoutine()
				.id(3L)
				.type(DAILY)
				.isAchieve(false)
				.achieveCount(beforeAchieveCount)
				.member(member)
				.build();

		doReturn(member).when(memberFinder).findById(member.getId());
		doReturn(memberRoutine).when(memberRoutineFinder).findById(memberRoutine.getId());

		MemberRoutineAchieveServiceRequest request = MemberRoutineAchieveServiceRequest.of(
				member.getId(),
				memberRoutine.getId()
		);

		// when
		memberRoutineService.achieveMemberRoutine(request);

		// then
		assertThat(memberRoutine.isAchieve()).isTrue();
		assertThat(memberRoutine.getAchieveCount()).isEqualTo(beforeAchieveCount + 1);
		assertThat(member.getCottonInfo().getDailyCottonCount()).isEqualTo(beforeCottonCount + 1);
	}

	@Test
	@DisplayName("[성공] 행복 루틴을 달성하면 달성 횟수와 행복 솜 뭉치 개수가 1만큼 증가한다.")
	void shouldUpdateAchieveCountAndCottonCountWhenAchieveHappinessRoutine() {
		// given
		int beforeCottonCount = 0;
		int beforeAchieveCount = 0;

		Member member = MemberFixture.member().id(1L).dailyCotton(beforeCottonCount).build();
		MemberRoutine memberRoutine = MemberRoutineFixture.memberRoutine()
				.id(3L)
				.type(CHALLENGE)
				.isAchieve(false)
				.achieveCount(beforeAchieveCount)
				.member(member)
				.build();

		doReturn(member).when(memberFinder).findById(member.getId());
		doReturn(memberRoutine).when(memberRoutineFinder).findById(memberRoutine.getId());

		MemberRoutineAchieveServiceRequest request = MemberRoutineAchieveServiceRequest.of(
				member.getId(),
				memberRoutine.getId()
		);

		// when
		memberRoutineService.achieveMemberRoutine(request);

		// then
		assertThat(memberRoutine.isAchieve()).isTrue();
		assertThat(memberRoutine.getAchieveCount()).isEqualTo(beforeAchieveCount + 1);
		assertThat(member.getCottonInfo().getHappinessCottonCount()).isEqualTo(beforeCottonCount + 1);
	}

	@Test
	@DisplayName("[성공] 달성한 데일리 루틴을 달성 초기화한다.")
	void updateAchieveFalseAchievedMemberRoutine() {
		// given
		List<MemberRoutine> memberRoutines = List.of(
				MemberRoutineFixture.memberRoutine().id(1L).isAchieve(true).build(),
				MemberRoutineFixture.memberRoutine().id(2L).isAchieve(true).build()
		);

		doReturn(memberRoutines).when(memberRoutineFinder).findAchieved();

		// when
		memberRoutineService.initDailyRoutines();

		// then
		assertThat(memberRoutines.get(0).isAchieve()).isFalse();
		assertThat(memberRoutines.get(1).isAchieve()).isFalse();
	}

}