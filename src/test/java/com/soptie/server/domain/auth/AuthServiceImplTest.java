package com.soptie.server.domain.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

	@InjectMocks
	private AuthService authService;

	@Test
	@DisplayName("로그아웃을 하면 리프레시 토큰 값이 null이 된다.")
	void resetRefreshTokenIfSignOut() { //TODO: 테스트
		// given

		// when

		// then
	}

	@Test
	@DisplayName("파러미터로 받은 리프레시 토큰을 가지고 있는 멤버가 있다면 액세스 토큰을 재발급 해준다.")
	void reissueTokenIfMemberHasSameRefreshToken() { //TODO: 테스트
		// given

		// when

		// then
	}
}
