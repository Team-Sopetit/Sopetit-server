package com.soptie.server.domain.memberdoll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.domain.member.MemberService;
import com.soptie.server.persistence.repository.DollRepository;
import com.soptie.server.persistence.repository.MemberDollRepository;

@ExtendWith(MockitoExtension.class)
class MemberDollServiceImplTest {

	@InjectMocks
	private MemberService memberService;

	@Mock
	private DollRepository dollRepository;

	@Mock
	private MemberDollRepository memberDollRepository;

	@Test
	@DisplayName("멤버 인형을 생성하고 이를 멤버의 멤버 인형으로 설정한다.")
	void createMemberDollAndSetMember() { //TODO: 테스트
		// given

		// when

		// then
	}
}
