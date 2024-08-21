package com.soptie.server.domain.member;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Member {
	private Long id;
	private Social socialInfo;
	private String refreshToken;
	private MemberCotton cottonInfo;

	public void resetRefreshToken() {
		this.refreshToken = null;
	}

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
