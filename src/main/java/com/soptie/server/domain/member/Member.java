package com.soptie.server.domain.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	private Long id;
	private Social socialInfo;
	private String refreshToken;
	private MemberCotton cottonInfo;

	public Member(SocialType socialType, String socialId) {
		this.socialInfo = new Social(socialType, socialId);
		this.cottonInfo = new MemberCotton(0, 0);
	}

	public void resetRefreshToken() {
		this.refreshToken = null;
	}

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
