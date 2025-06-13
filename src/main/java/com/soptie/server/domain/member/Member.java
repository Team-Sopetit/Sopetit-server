package com.soptie.server.domain.member;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	private Long id;

	@NonNull
	private Social socialInfo;

	private String refreshToken;

	@NonNull
	private MemberCotton cottonInfo;

	private LocalDateTime createdAt;

	private LocalDateTime lastVisitDateTime;

	public Member(SocialType socialType, String socialId) {
		this.socialInfo = new Social(socialType, socialId);
		this.cottonInfo = new MemberCotton(0, 0);
		this.lastVisitDateTime = LocalDateTime.now();
	}
}
