package com.soptie.server.member.service;

import com.soptie.server.conversation.entity.Conversation;
import com.soptie.server.conversation.repository.ConversationRepository;
import com.soptie.server.doll.entity.Doll;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.dto.MemberHomeInfoResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.CottonType;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.service.MemberDollServiceImpl;
import com.soptie.server.memberRoutine.service.daily.MemberDailyRoutineServiceImpl;
import com.soptie.server.support.fixture.ConversationFixture;
import com.soptie.server.support.fixture.DollFixture;
import com.soptie.server.support.fixture.MemberDollFixture;
import com.soptie.server.support.fixture.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.soptie.server.doll.entity.DollType.BROWN;
import static com.soptie.server.member.message.ErrorCode.NOT_ENOUGH_COTTON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberDailyRoutineServiceImpl memberDailyRoutineService;

    @Mock
    private MemberDollServiceImpl memberDollService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ConversationRepository conversationRepository;

    @Test
    @DisplayName("멤버 프로필 생성 시, 멤버 데일리 루틴 생성과 멤버 인형 생성 메소드를 호출한다.")
    void 멤버_프로필을_생성하면서_멤버_데일리_루틴과_멤버_인형을_생성한다() {
        // given
        long memberId = 1L;
        Member member = member(memberId);
        DollType dollType = BROWN;
        String name = "memberDoll";
        List<Long> routines = List.of(2L, 3L, 4L);
        MemberProfileRequest request = new MemberProfileRequest(dollType, name, routines);
        doNothing().when(memberDailyRoutineService).createMemberDailyRoutines(member, List.of(2L, 3L, 4L));
        doNothing().when(memberDollService).createMemberDoll(member, dollType, name);

        // when
        memberService.createMemberProfile(memberId, request);

        // then
        verify(memberDailyRoutineService).createMemberDailyRoutines(member, routines);
        verify(memberDollService).createMemberDoll(member, dollType, name);
    }

    @Test
    @DisplayName("솜뭉치 개수가 양수일 때 솜뭉치를 줄 수 있다.")
    void canGiveCottonWhenCottonCountIsPositive() {
        // given
        long memberId = 1L;
        MemberDoll memberDoll = new MemberDoll(1L);
        Member member = member(memberId, memberDoll, 1);
        int beforeCotton = member.getCottonInfo().getDailyCottonCount();

        // when
        memberService.giveCotton(member.getId(), CottonType.DAILY);

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
        assertThatThrownBy(() -> memberService.giveCotton(member.getId(), CottonType.DAILY))
                .isInstanceOf(MemberException.class)
                .hasMessage("[MemberException] : " + NOT_ENOUGH_COTTON.getMessage());
    }

    @Test
    @DisplayName("멤버의 멤버 인형 정보와 솜뭉치 개수를 가져온다.")
    void 멤버_프로필_정보를_가져온다() {
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
        MemberHomeInfoResponse result = memberService.getMemberHomeInfo(memberId);

        // then
        assertThat(MemberHomeInfoResponse.of(member, conversations.stream().map(Conversation::getContent).toList())).isEqualTo(result);
    }


    private Member member(long memberId) {
        Member member = MemberFixture.member().id(memberId).build();
        doReturn(Optional.of(member)).when(memberRepository).findById(memberId);
        return member;
    }

    private Member member(long memberId, MemberDoll memberDoll) {
        Member member = MemberFixture.member().id(memberId).memberDoll(memberDoll).build();
        doReturn(Optional.of(member)).when(memberRepository).findById(memberId);
        return member;
    }

    private Member member(long memberId, MemberDoll memberDoll, int dailyCottonCount) {
        Member member = MemberFixture.member().id(memberId).memberDoll(memberDoll).dailyCotton(dailyCottonCount).build();
        doReturn(Optional.of(member)).when(memberRepository).findById(memberId);
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
        doReturn(conversations).when(conversationRepository).findAll();
        return conversations;
    }
}
