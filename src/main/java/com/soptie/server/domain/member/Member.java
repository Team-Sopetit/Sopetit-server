package com.soptie.server.domain.member;

import java.time.LocalDateTime;

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
	private LocalDateTime createdAt;
	private String fcmToken;

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

	public void updateFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
}
