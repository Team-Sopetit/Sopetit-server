package com.soptie.server.domain.memberroutine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class MemberRoutineServiceTest {
	@InjectMocks
	private MemberRoutineService memberRoutineService;

	@Test
	@DisplayName("[성공] 회원이 추가한 루틴을 조회횐다.")
	void getMemberRoutines() { //TODO: 테스트
		// given

		// when

		// then
	}
}
