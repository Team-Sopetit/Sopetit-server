package com.soptie.server.member.service;

import com.soptie.server.doll.entity.Doll;
import com.soptie.server.member.entity.CottonType;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.fixture.MemberFixture;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberDoll.entity.MemberDoll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.soptie.server.member.message.ErrorCode.NOT_ENOUGH_COTTON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @InjectMocks
    private MemberServiceImpl memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("솜뭉치 개수가 양수일 때 솜뭉치를 줄 수 있다.")
    void canGiveCottonWhenCottonCountIsPositive() {
        // given
        Member member = getMember().get();
        doReturn(Optional.of(member)).when(memberRepository).findById(1L);
        member.addDailyCotton();

        // when
        memberService.giveCotton(member.getId(), CottonType.DAILY);

        // then
        assertThat(member.getCottonInfo().getDailyCottonCount()).isZero();
    }

    @Test
    @DisplayName("솜뭉치 개수가 0일 때 솜뭉치를 주려 하면 예외가 발생한다.")
    void occurExceptionGiveCottonWhenCottonCountIsZero() {
        // when, then
        Member member = getMember().get();
        doReturn(Optional.of(member)).when(memberRepository).findById(1L);
        assertThatThrownBy(() -> memberService.giveCotton(member.getId(), CottonType.DAILY))
                .isInstanceOf(MemberException.class)
                .hasMessage("[MemberException] : " + NOT_ENOUGH_COTTON.getMessage());
    }

    Optional<Member> getMember() {
        Member member = MemberFixture.getMember();
        return Optional.of(member);
    }
}
