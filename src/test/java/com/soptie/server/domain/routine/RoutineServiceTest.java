package com.soptie.server.domain.routine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoutineServiceTest {
	@InjectMocks
	RoutineService routineService;

	@Test
	@DisplayName("[성공] 회원의 진행 유무를 포함한 루틴을 테마별로 조회한다.")
	void getRoutinesByThemeId() { //TODO: test
		// given

		// when

		// then
	}
}
