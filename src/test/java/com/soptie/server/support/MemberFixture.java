package com.soptie.server.support;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberDoll.entity.MemberDoll;

public class MemberFixture {

	private Long id;
	private MemberDoll memberDoll;
	private int dailyCottonCount = 0;

	private MemberFixture() {
	}

	public static MemberFixture member() {
		return new MemberFixture();
	}

	public MemberFixture id(Long id) {
		this.id = id;
		return this;
	}

	public MemberFixture dailyCotton(int dailyCottonCount) {
		this.dailyCottonCount = dailyCottonCount;
		return this;
	}

	public MemberFixture memberDoll(MemberDoll memberDoll) {
		this.memberDoll = memberDoll;
		return this;
	}

	public Member build() {
		return new Member(id, memberDoll, dailyCottonCount);
	}
}
