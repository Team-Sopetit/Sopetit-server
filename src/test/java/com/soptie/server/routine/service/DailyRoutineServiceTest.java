package com.soptie.server.routine.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.domain.routine.RoutineService;
import com.soptie.server.persistence.adapter.MemberFinder;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.adapter.MemberRoutineFinder;
import com.soptie.server.persistence.entity.MemberRoutine;
import com.soptie.server.persistence.adapter.RoutineFinder;
import com.soptie.server.persistence.entity.Routine;
import com.soptie.server.persistence.entity.RoutineType;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.fixture.MemberRoutineFixture;
import com.soptie.server.support.fixture.RoutineFixture;

@ExtendWith(MockitoExtension.class)
class DailyRoutineServiceTest {

	@InjectMocks
	RoutineService routineService;

	@Mock
	RoutineFinder routineFinder;

	@Mock
	MemberRoutineFinder memberRoutineFinder;

	@Mock
	MemberFinder memberFinder;

	@Test
	@DisplayName("[성공] 회원 루틴 유무에 따라 Map 타입으로 루틴 정보를 반환한다.")
	void acquireRoutineToMember() {
		// given
		long themeId = 0L;
		long memberId = 0L;
		Member member = MemberFixture.member().build();
		List<Routine> routines = List.of(
			RoutineFixture.routine().id(1L).build(),
			RoutineFixture.routine().id(2L).build(),
			RoutineFixture.routine().id(3L).build(),
			RoutineFixture.routine().id(4L).build(),
			RoutineFixture.routine().id(5L).build()
		);
		List<MemberRoutine> memberRoutines = List.of(
			MemberRoutineFixture.memberRoutine().routineId(1L).build(),
			MemberRoutineFixture.memberRoutine().routineId(2L).build(),
			MemberRoutineFixture.memberRoutine().routineId(3L).build()
		);

		doReturn(routines).when(routineFinder).findAllByTypeAndThemeId(RoutineType.DAILY, themeId);
		doReturn(member).when(memberFinder).findById(memberId);
		doReturn(memberRoutines).when(memberRoutineFinder).findAllByMemberAndType(member, RoutineType.DAILY);

		// when
		Map<Boolean, List<Routine>> result = routineService.acquireAllInDailyByThemeAndMember(memberId, themeId);

		// then
		assertThat(result.get(true)).hasSize(3);
		assertThat(result.get(false)).hasSize(2);
	}
}
