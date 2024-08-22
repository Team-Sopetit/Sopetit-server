package com.soptie.server.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.support.IntegrationTest;

@IntegrationTest
@Transactional
public class MemberServiceIntegrationTest {

	@Autowired
	private MemberService memberService;

	@Test
	@DisplayName("[성공] 응답이 유효하면 회원 프로필이 생성된다.")
	void createMemberProfileSuccessfully() { //TODO: 테스트
		// given

		// when

		// then
	}
}
