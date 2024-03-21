package com.soptie.server.memberRoutine.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;
import com.soptie.server.support.DailyRoutineFixture;
import com.soptie.server.support.DailyThemeFixture;
import com.soptie.server.support.MemberDailyRoutineFixture;
import com.soptie.server.support.MemberFixture;
import com.soptie.server.support.RepositoryTest;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@RepositoryTest
class MemberDailyRoutineRepositoryTest {

	@Autowired
	MemberDailyRoutineRepository memberDailyRoutineRepository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	DailyRoutineRepository dailyRoutineRepository;
	@Autowired
	DailyThemeRepository dailyThemeRepository;

	@AfterEach
	void afterEach() {
		memberDailyRoutineRepository.deleteAllInBatch();
		memberDailyRoutineRepository.deleteAllInBatch();
		dailyRoutineRepository.deleteAllInBatch();
		dailyThemeRepository.deleteAllInBatch();
	}

	@Test
	void 회원으로_조회한_데일리_루틴_리스트를_미달성부터_달성개수_내림차순_내용_오름차순으로_조회한다() {
		// given
		Member savedMember = memberRepository.save(MemberFixture.member().build());
		DailyTheme savedTheme = dailyThemeRepository.save(DailyThemeFixture.dailyTheme().name("테마").build());

		DailyRoutine savedRoutine1 = savedRoutine("가나다", savedTheme);
		DailyRoutine savedRoutine2 = savedRoutine("나다", savedTheme);
		DailyRoutine savedRoutine3 = savedRoutine("다", savedTheme);

		MemberDailyRoutine secondDailyRoutine1 = savedMemberRoutine(savedMember, false, 5, savedRoutine3);
		MemberDailyRoutine firstDailyRoutine = savedMemberRoutine(savedMember, false, 5, savedRoutine2);
		MemberDailyRoutine thirdDailyRoutine3 = savedMemberRoutine(savedMember, false, 3, savedRoutine1);
		MemberDailyRoutine sixthDailyRoutine4 = savedMemberRoutine(savedMember, true, 1, savedRoutine1);
		MemberDailyRoutine fifthDailyRoutine5 = savedMemberRoutine(savedMember, true, 3, savedRoutine2);
		MemberDailyRoutine fourthDailyRoutine6 = savedMemberRoutine(savedMember, true, 5, savedRoutine3);

		// when
		List<MemberDailyRoutine> actual = memberDailyRoutineRepository.findAllByMember(savedMember);

		// then
		assertThat(actual).hasSize(6);
		assertThat(actual.get(0)).isEqualTo(firstDailyRoutine);
		assertThat(actual.get(1)).isEqualTo(secondDailyRoutine1);
		assertThat(actual.get(2)).isEqualTo(thirdDailyRoutine3);
		assertThat(actual.get(3)).isEqualTo(fourthDailyRoutine6);
		assertThat(actual.get(4)).isEqualTo(fifthDailyRoutine5);
		assertThat(actual.get(5)).isEqualTo(sixthDailyRoutine4);
	}

	private MemberDailyRoutine savedMemberRoutine(
			Member member,
			boolean isAchieve,
			int achieveCount,
			DailyRoutine routine
	) {
		return memberDailyRoutineRepository.save(MemberDailyRoutineFixture
				.memberRoutine()
				.member(member)
				.isAchieve(isAchieve)
				.achieveCount(achieveCount)
				.routine(routine)
				.build());
	}

	private DailyRoutine savedRoutine(String content, DailyTheme theme) {
		return dailyRoutineRepository.save(DailyRoutineFixture.dailyRoutine().content(content).theme(theme).build());
	}
}