package com.soptie.server.memberDoll.service;

import com.soptie.server.doll.entity.Doll;
import com.soptie.server.doll.repository.DollRepository;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberDoll.repository.MemberDollRepository;
import com.soptie.server.support.MemberFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.soptie.server.doll.entity.DollType.BROWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

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
    void 멤버_인형을_생성하고_이를_멤버의_인형으로_세팅한다() {
        // given
        Long id = 1L;
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
