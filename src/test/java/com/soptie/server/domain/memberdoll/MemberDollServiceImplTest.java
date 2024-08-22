package com.soptie.server.domain.memberdoll;

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
/*
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
 */
}
