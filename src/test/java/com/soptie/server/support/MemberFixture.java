package com.soptie.server.support;

import com.soptie.server.member.entity.Member;

public class MemberFixture {

	private Long id;

	private MemberFixture() {
	}

	public static MemberFixture member() {
		return new MemberFixture();
	}

	public MemberFixture id(Long id) {
		this.id = id;
		return this;
	}

	public Member build() {
		return new Member(id);
	}
}
