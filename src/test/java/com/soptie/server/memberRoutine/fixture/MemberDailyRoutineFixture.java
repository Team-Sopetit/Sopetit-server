package com.soptie.server.memberRoutine.fixture;

import java.util.List;
import java.util.stream.Stream;

import com.soptie.server.memberRoutine.dto.MemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutinesResponse;

public class MemberDailyRoutineFixture {


	public static MemberDailyRoutineResponse createMemberDailyRoutineResponseDTO() {
		return new MemberDailyRoutineResponse(1L);
	}

	public static MemberDailyRoutinesResponse createMemberDailyRoutinesResponseDTO() {
		return new MemberDailyRoutinesResponse(createDailyRoutineResponses());
	}

	private static List<MemberDailyRoutinesResponse.MemberDailyRoutineResponse> createDailyRoutineResponses() {
		return Stream.iterate(1, i -> i + 1).limit(5)
				.map(MemberDailyRoutineFixture::createDailyRoutineResponse)
				.toList();
	}

	private static MemberDailyRoutinesResponse.MemberDailyRoutineResponse createDailyRoutineResponse(int i) {
		return MemberDailyRoutinesResponse.MemberDailyRoutineResponse.builder()
				.routineId(i)
				.content("routine content" + i)
				.iconImageUrl("https://...")
				.achieveCount(i)
				.isAchieve(i % 2 == 0)
				.build();
	}
}
