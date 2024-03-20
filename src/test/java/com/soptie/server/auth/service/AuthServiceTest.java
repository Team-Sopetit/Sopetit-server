package com.soptie.server.auth.service;

import com.soptie.server.auth.dto.TokenResponse;
import com.soptie.server.auth.jwt.JwtTokenProvider;
import com.soptie.server.auth.jwt.UserAuthentication;
import com.soptie.server.common.config.ValueConfig;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.fixture.MemberFixture;
import com.soptie.server.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ValueConfig valueConfig;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

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

    @Test
    @DisplayName("파러미터로 받은 리프레시 토큰을 가지고 있는 멤버가 있다면 액세스 토큰과 리프레시 토큰을 재발급 해준다.")
    void 리프레시_토큰을_가지고_있는_멤버가_있다면_토큰_세트를_재발급_해줄_수_있다() {
        // given
        Member member = getMember().get();
        doReturn(Optional.of(member)).when(memberRepository).findByRefreshToken("refreshToken");
        doReturn("Bearer ").when(valueConfig).getBEARER_HEADER();
        doReturn("").when(valueConfig).getBLANK();
        doReturn(60000L).when(valueConfig).getAccessTokenExpired();
        doReturn("token").when(jwtTokenProvider)
                .generateToken(new UserAuthentication(member.getId(), null, null), 60000L);

        // when
        TokenResponse result = authService.reissueToken("Bearer refreshToken");

        // then
        assertThat(result).isEqualTo(TokenResponse.of("token"));
    }



    Optional<Member> getMember() {
        Member member = MemberFixture.getMember();
        return Optional.of(member);
    }
}
