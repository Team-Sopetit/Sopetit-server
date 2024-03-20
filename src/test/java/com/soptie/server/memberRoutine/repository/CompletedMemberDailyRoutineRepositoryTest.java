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
import com.soptie.server.support.CompletedMemberDailyRoutineFixture;
import com.soptie.server.support.DailyRoutineFixture;
import com.soptie.server.support.DailyThemeFixture;
import com.soptie.server.support.MemberFixture;
import com.soptie.server.support.RepositoryTest;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
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

	@AfterEach
	void afterEach() {
		completedMemberDailyRoutineRepository.deleteAllInBatch();
		memberRepository.deleteAllInBatch();
		dailyRoutineRepository.deleteAllInBatch();
		dailyThemeRepository.deleteAllInBatch();
	}

	@Test
	void 루틴과_회원으로_삭제된_데일리_루틴을_조회한다() {
		// given
		Member savedMember = memberRepository.save(MemberFixture.member().build());
		DailyTheme savedTheme = dailyThemeRepository
				.save(DailyThemeFixture.dailyTheme().name("테마").imageUrl("url").build());
		DailyRoutine savedRoutine = dailyRoutineRepository
				.save(DailyRoutineFixture.dailyRoutine().content("루틴").theme(savedTheme).build());

		completedMemberDailyRoutineRepository.save(CompletedMemberDailyRoutineFixture
				.completedMemberDailyRoutine()
				.member(savedMember)
				.routine(savedRoutine)
				.build()
		);

		// when
		Optional<CompletedMemberDailyRoutine> actual = completedMemberDailyRoutineRepository
				.findByMemberAndRoutine(savedMember, savedRoutine);

		// then
		assertThat(actual).isNotEmpty();

		CompletedMemberDailyRoutine found = actual.get();
		assertThat(found.getMember().getId()).isEqualTo(savedMember.getId());
		assertThat(found.getRoutine().getId()).isEqualTo(savedRoutine.getId());
	}
}