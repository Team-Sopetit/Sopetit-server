package com.soptie.server.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@InjectMocks
	private MemberService memberService;

	@Test
	@DisplayName("멤버 프로필 생성 시, 멤버 데일리 루틴 생성과 멤버 인형 생성 메소드를 호출한다.")
	void createMemberProfileSuccessfully() { //TODO: 테스트
		// given

		// when

		// then
	}

	@Test
	@DisplayName("솜뭉치 개수가 양수일 때 솜뭉치를 줄 수 있다.")
	void canGiveCottonWhenCottonCountIsPositive() { //TODO: 테스트
		// given

		// when

		// then
	}

	@Test
	@DisplayName("솜뭉치 개수가 0일 때 솜뭉치를 주려 하면 예외가 발생한다.")
	void occurExceptionGiveCottonWhenCottonCountIsZero() { //TODO: 테스트
		// given

		// when

		// then
	}

	@Test
	@DisplayName("멤버의 멤버 인형 정보와 솜뭉치 개수를 가져온다.")
	void getMemberProfile() { //TODO: 테스트
		// given

		// when

		// then
	}
}
