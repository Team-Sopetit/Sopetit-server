package com.soptie.server.memberRoutine.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.entity.daily.CompletedMemberDailyRoutine;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;
import com.soptie.server.support.fixture.CompletedMemberDailyRoutineFixture;
import com.soptie.server.support.fixture.DailyRoutineFixture;
import com.soptie.server.support.fixture.DailyThemeFixture;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.RepositoryTest;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@RepositoryTest
class CompletedMemberDailyRoutineRepositoryTest {

	@Autowired
	CompletedMemberDailyRoutineRepository completedMemberRoutineRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	DailyRoutineRepository dailyRoutineRepository;
	@Autowired
	DailyThemeRepository dailyThemeRepository;

	@AfterEach
	void afterEach() {
		completedMemberRoutineRepository.deleteAllInBatch();
		memberRepository.deleteAllInBatch();
		dailyRoutineRepository.deleteAllInBatch();
		dailyThemeRepository.deleteAllInBatch();
	}

	@Test
	void 루틴과_회원으로_삭제된_데일리_루틴을_조회한다() {
		// given
		Member savedMember = memberRepository.save(MemberFixture.member().build());
		DailyTheme savedTheme = dailyThemeRepository.save(DailyThemeFixture.dailyTheme().name("테마").build());
		DailyRoutine savedRoutine = savedRoutine("루틴", savedTheme);
		savedCompletedMemberDailyRoutine(savedMember, savedRoutine);

		// when
		Optional<CompletedMemberDailyRoutine> actual = completedMemberRoutineRepository
				.findByMemberAndRoutine(savedMember, savedRoutine);

		// then
		assertThat(actual).isNotEmpty();

		CompletedMemberDailyRoutine found = actual.get();
		assertThat(found.getMember().getId()).isEqualTo(savedMember.getId());
		assertThat(found.getRoutine().getId()).isEqualTo(savedRoutine.getId());
	}

	private DailyRoutine savedRoutine(String content, DailyTheme theme) {
		return dailyRoutineRepository.save(DailyRoutineFixture.dailyRoutine().content(content).theme(theme).build());
	}

	private CompletedMemberDailyRoutine savedCompletedMemberDailyRoutine(Member member, DailyRoutine routine) {
		return completedMemberRoutineRepository.save(CompletedMemberDailyRoutineFixture
				.completedMemberDailyRoutine()
				.member(member)
				.routine(routine)
				.build());
	}
}