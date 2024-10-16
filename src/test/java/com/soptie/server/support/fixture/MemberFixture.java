package com.soptie.server.support.fixture;

import java.util.UUID;

import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.member.SocialType;

public class MemberFixture {

	public static Member createDefault() {
		return new Member(SocialType.KAKAO, UUID.randomUUID().toString());
	}
}
