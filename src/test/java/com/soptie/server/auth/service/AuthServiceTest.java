package com.soptie.server.auth.service;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.fixture.MemberFixture;
import com.soptie.server.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 탈퇴를 하면 리프레시 토큰 값이 null이 된다.")
    void 회원_탈퇴를_하면_리프레시_토큰을_비울_수_있다() {
        // given
        Member member = getMember().get();
        doReturn(Optional.of(member)).when(memberRepository).findById(1L);
        member.updateRefreshToken("refreshToken");

        // when
        authService.signOut(member.getId());

        // then
        assertThat(member.getRefreshToken()).isNull();
    }

    Optional<Member> getMember() {
        Member member = MemberFixture.getMember();
        return Optional.of(member);
    }
}
