package com.soptie.server.memberRoutine.fixture;

import java.util.List;
import java.util.stream.Stream;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutinesResponse;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.fixture.DailyRoutineFixture;

public class MemberDailyRoutineFixture {

	private static final Member MEMBER = new Member();

	public static MemberDailyRoutineResponse createMemberDailyRoutineResponseDTO() {
		return new MemberDailyRoutineResponse(1L);
	}

	public static MemberDailyRoutinesResponse createMemberDailyRoutinesResponseDTO() {
		return MemberDailyRoutinesResponse.of(createMemberDailyRoutines());
	}

	private static List<MemberDailyRoutine> createMemberDailyRoutines() {
		return Stream.iterate(1, i -> i + 1).limit(5)
			.map(MemberDailyRoutineFixture::createMemberDailyRoutine).toList();
	}

	private static MemberDailyRoutine createMemberDailyRoutine(int i) {
		return new MemberDailyRoutine((long)i, i, i % 2 == 0, MEMBER, createDailyRoutine(i));
	}

	private static DailyRoutine createDailyRoutine(int i) {
		DailyTheme theme = DailyRoutineFixture.createDailyTheme(
			(long)i, "name-test", "image-url", "image-url");
		return DailyRoutineFixture.createDailyRoutine((long)i, "content-test", theme);
	}
}
