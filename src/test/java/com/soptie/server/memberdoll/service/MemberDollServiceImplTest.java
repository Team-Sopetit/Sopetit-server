package com.soptie.server.memberdoll.service;

import static com.soptie.server.doll.entity.DollType.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.doll.entity.Doll;
import com.soptie.server.doll.repository.DollRepository;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberdoll.repository.MemberDollRepository;
import com.soptie.server.support.fixture.MemberFixture;

@ExtendWith(MockitoExtension.class)
class MemberDollServiceImplTest {

	@InjectMocks
	private MemberDollServiceImpl memberDollService;

	@Mock
	private DollRepository dollRepository;

	@Mock
	private MemberDollRepository memberDollRepository;

	@Test
	@DisplayName("멤버 인형을 생성하고 이를 멤버의 멤버 인형으로 설정한다.")
	void addMemberDoll() {
		// given
		long id = 1L;
		String name = "brownie";
		Member member = member(id);
		doReturn(Optional.of(new Doll())).when(dollRepository).findByDollType(BROWN);

		// when
		memberDollService.createMemberDoll(member, BROWN, name);

		// then
		assertThat(member.getMemberDoll().getName()).isEqualTo(name);
	}

	private Member member(long memberId) {
		Member member = MemberFixture.member().id(memberId).build();
		return member;
	}
}
