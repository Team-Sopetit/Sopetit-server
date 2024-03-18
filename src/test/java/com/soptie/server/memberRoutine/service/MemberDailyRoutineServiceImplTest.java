package com.soptie.server.memberRoutine.service;

import static com.soptie.server.routine.message.ErrorCode.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.repository.CompletedMemberDailyRoutineRepository;
import com.soptie.server.memberRoutine.repository.MemberDailyRoutineRepository;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.support.DailyRoutineFixture;
import com.soptie.server.support.MemberDailyRoutineFixture;
import com.soptie.server.support.MemberFixture;

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
		final MemberDailyRoutineRequest request = new MemberDailyRoutineRequest(routineId);
		final MemberDailyRoutineResponse actual = memberDailyRoutineService.createMemberDailyRoutine(memberId, request);

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
		final MemberDailyRoutineRequest request = new MemberDailyRoutineRequest(routineId);
		assertThatThrownBy(() -> memberDailyRoutineService.createMemberDailyRoutine(memberId, request))
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
		final MemberDailyRoutineRequest request = new MemberDailyRoutineRequest(2L);
		assertThatThrownBy(() -> memberDailyRoutineService.createMemberDailyRoutine(memberId, request))
				.isInstanceOf(RoutineException.class)
				.hasMessage("[RoutineException] : " + CANNOT_ADD_MEMBER_ROUTINE.getMessage());
	}

	private Member member(long memberId) {
		Member member = MemberFixture.member().id(memberId).build();
		doReturn(Optional.of(member)).when(memberRepository).findById(memberId);
		return member;
	}

	private DailyRoutine dailyRoutine(long routineId) {
		DailyRoutine dailyRoutine = DailyRoutineFixture.dailyRoutine().id(routineId).build();
		doReturn(Optional.of(dailyRoutine)).when(dailyRoutineRepository).findById(routineId);
		return dailyRoutine;
	}

}