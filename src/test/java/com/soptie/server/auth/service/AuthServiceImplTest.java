package com.soptie.server.auth.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.domain.auth.TokenGetServiceRequest;
import com.soptie.server.domain.auth.TokenGetServiceResponse;
import com.soptie.server.config.ValueConfig;
import com.soptie.server.domain.auth.AuthServiceImpl;
import com.soptie.server.api.web.jwt.JwtTokenProvider;
import com.soptie.server.api.web.jwt.UserAuthentication;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.repository.MemberRepository;
import com.soptie.server.support.fixture.MemberFixture;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

	@InjectMocks
	private AuthServiceImpl authService;

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private ValueConfig valueConfig;

	@Mock
	private JwtTokenProvider jwtTokenProvider;

	@Test
	@DisplayName("로그아웃을 하면 리프레시 토큰 값이 null이 된다.")
	void emptyRefreshTokenAfterLogout() {
		// given
		long memberId = 1L;
		Member member = member(memberId);
		doReturn(Optional.of(member)).when(memberRepository).findById(memberId);
		member.updateRefreshToken("refreshToken");

		// when
		authService.signOut(member.getId());

		// then
		assertThat(member.getRefreshToken()).isNull();
	}

	@Test
	@DisplayName("파러미터로 받은 리프레시 토큰을 가지고 있는 멤버가 있다면 액세스 토큰을 재발급 해준다.")
	void refreshAccessTokenForMemberWithRefreshToken() {
		// given
		long memberId = 1L;
		Member member = member(memberId);
		String token = "refreshToken";
		doReturn(Optional.of(member)).when(memberRepository).findByRefreshToken(token);
		// doReturn("Bearer ").when(valueConfig).getBEARER_HEADER();
		// doReturn("").when(valueConfig).getBLANK();
		doReturn(60000L).when(valueConfig).getAccessTokenExpired();
		doReturn(token).when(jwtTokenProvider)
			.generateToken(new UserAuthentication(member.getId(), null, null), 60000L);

		// when
		TokenGetServiceResponse result = authService.reissueToken(TokenGetServiceRequest.of("Bearer " + token));

		// then
		assertThat(result).isEqualTo(TokenGetServiceResponse.of(token));
	}

	private Member member(long memberId) {
		Member member = MemberFixture.member().id(memberId).build();
		return member;
	}
}
