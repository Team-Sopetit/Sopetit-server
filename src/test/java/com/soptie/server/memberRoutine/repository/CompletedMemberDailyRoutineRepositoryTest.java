package com.soptie.server.memberRoutine.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.entity.daily.CompletedMemberDailyRoutine;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;
import com.soptie.server.support.CompletedMemberDailyRoutineFixture;
import com.soptie.server.support.DailyRoutineFixture;
import com.soptie.server.support.DailyThemeFixture;
import com.soptie.server.support.MemberFixture;
import com.soptie.server.support.RepositoryTest;

@RepositoryTest
class CompletedMemberDailyRoutineRepositoryTest {

	@Autowired
	CompletedMemberDailyRoutineRepository completedMemberDailyRoutineRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	DailyRoutineRepository dailyRoutineRepository;
	@Autowired
	DailyThemeRepository dailyThemeRepository;

	@Test
	void 루틴과_회원으로_조회할_수_있다() {
		// given
		Member member = memberRepository.save(MemberFixture.member().build());
		DailyTheme dailyTheme = dailyThemeRepository.save(DailyThemeFixture.dailyTheme().id(1L).name("daily theme").build());
		DailyRoutine dailyRoutine = dailyRoutineRepository
				.save(DailyRoutineFixture.dailyRoutine().theme(dailyTheme).content("this is routine").build());

		CompletedMemberDailyRoutine completedMemberDailyRoutine = CompletedMemberDailyRoutineFixture
				.completedMemberDailyRoutine().member(member).routine(dailyRoutine).build();
		completedMemberDailyRoutineRepository.save(completedMemberDailyRoutine);

		// when
		Optional<CompletedMemberDailyRoutine> actual = completedMemberDailyRoutineRepository
				.findByMemberAndRoutine(member, dailyRoutine);

		// then
		assertThat(actual).isNotEmpty();

		CompletedMemberDailyRoutine found = actual.get();
		assertThat(found.getMember().getId()).isEqualTo(member.getId());
		assertThat(found.getRoutine().getId()).isEqualTo(dailyRoutine.getId());
	}
}