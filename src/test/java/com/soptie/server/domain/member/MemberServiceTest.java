package com.soptie.server.domain.member;

import static com.soptie.server.common.message.MemberErrorCode.*;
import static com.soptie.server.domain.doll.DollType.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.domain.doll.DollType;
import com.soptie.server.persistence.adapter.ConversationFinder;
import com.soptie.server.support.fixture.ConversationFixture;
import com.soptie.server.support.fixture.DollFixture;
import com.soptie.server.support.fixture.MemberDollFixture;
import com.soptie.server.support.fixture.MemberFixture;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@InjectMocks
	private MemberService memberService;

	@Mock
	private MemberFinder memberFinder;

	@Mock
	private ConversationFinder conversationFinder;
/*
    @Test
    @DisplayName("멤버 프로필 생성 시, 멤버 데일리 루틴 생성과 멤버 인형 생성 메소드를 호출한다.")
    void 멤버_프로필을_생성하면서_멤버_데일리_루틴과_멤버_인형을_생성한다() {
        // given
        long memberId = 1L;
        Member member = member(memberId);
        DollType dollType = BROWN;
        String name = "memberDoll";
        List<Long> routines = List.of(2L, 3L, 4L);
        MemberProfileCreateRequest request = new MemberProfileCreateRequest(dollType, name, routines);
        doNothing().when(memberRoutineSaver).checkHasDeletedAndSave(member, List.of(2L, 3L, 4L));
        doNothing().when(memberService).createMemberDoll(member, dollType, name);

        // when
        memberService.createMemberProfile(MemberProfileCreateServiceRequest.of(memberId, request));

        // then
        verify(memberService).createDailyRoutines(member, routines);
        verify(memberService).createMemberDoll(member, dollType, name);
    }*/

	@Test
	@DisplayName("솜뭉치 개수가 양수일 때 솜뭉치를 줄 수 있다.")
	void canGiveCottonWhenCottonCountIsPositive() {
		// given
		long memberId = 1L;
		MemberDoll memberDoll = new MemberDoll(1L);
		Member member = member(memberId, memberDoll, 1);
		int beforeCotton = member.getCottonInfo().getDailyCottonCount();

		// when
		memberService.giveCotton(CottonGiveServiceRequest.of(member.getId(), CottonType.DAILY));

		// then
		assertThat(member.getCottonInfo().getDailyCottonCount()).isEqualTo(beforeCotton - 1);
	}

	@Test
	@DisplayName("솜뭉치 개수가 0일 때 솜뭉치를 주려 하면 예외가 발생한다.")
	void occurExceptionGiveCottonWhenCottonCountIsZero() {
		// given
		long memberId = 1L;
		MemberDoll memberDoll = new MemberDoll(1L);
		Member member = member(memberId, memberDoll);

		// when, then
		assertThatThrownBy(
			() -> memberService.giveCotton(CottonGiveServiceRequest.of(member.getId(), CottonType.DAILY)))
			.isInstanceOf(MemberException.class)
			.hasMessage("[MemberException] : " + NOT_ENOUGH_COTTON.getMessage());
	}

	@Test
	@DisplayName("멤버의 멤버 인형 정보와 솜뭉치 개수를 가져온다.")
	void acquireProfile() {
		// given
		long dollId = 1L;
		Doll doll = doll(dollId, BROWN, "faceImageUrl");
		long memberDollId = 2L;
		MemberDoll memberDoll = memberDoll(memberDollId, "memberDoll", 0, doll);
		long memberId = 3L;
		Member member = member(memberId, memberDoll);
		List<Long> conversationIds = List.of(1L, 2L);
		List<Conversation> conversations = conversations(conversationIds);

		// when
		MemberHomeInfoGetServiceResponse result = memberService.getMemberHomeInfo(
			MemberHomeInfoGetServiceRequest.of(memberId));

		// then
		assertThat(MemberHomeInfoGetServiceResponse.of(member,
			conversations.stream().map(Conversation::getContent).toList())).isEqualTo(result);
	}

	private Member member(long memberId) {
		Member member = MemberFixture.member().id(memberId).build();
		doReturn(member).when(memberFinder).findById(memberId);
		return member;
	}

	private Member member(long memberId, MemberDoll memberDoll) {
		Member member = MemberFixture.member().id(memberId).memberDoll(memberDoll).build();
		doReturn(member).when(memberFinder).findById(memberId);
		return member;
	}

	private Member member(long memberId, MemberDoll memberDoll, int dailyCottonCount) {
		Member member = MemberFixture.member()
			.id(memberId)
			.memberDoll(memberDoll)
			.dailyCotton(dailyCottonCount)
			.build();
		doReturn(member).when(memberFinder).findById(memberId);
		return member;
	}

	private MemberDoll memberDoll(long memberDollId, String name, int happinessCottonCount, Doll doll) {
		MemberDoll memberDoll = MemberDollFixture.memberDoll().id(memberDollId).name(name)
			.happinessCottonCount(happinessCottonCount).doll(doll).build();
		return memberDoll;
	}

	private Doll doll(Long id, DollType dollType, String faceImageUrl) {
		Doll doll = DollFixture.doll().id(id).dollType(dollType).faceImageUrl(faceImageUrl).build();
		return doll;
	}

	private List<Conversation> conversations(List<Long> conversationIds) {
		List<Conversation> conversations = conversationIds.stream()
			.map(conversationId -> ConversationFixture.conversation()
				.id(conversationId)
				.content("conversation" + conversationId)
				.build()
			).toList();
		doReturn(conversations).when(conversationFinder).findAll();
		return conversations;
	}
}
