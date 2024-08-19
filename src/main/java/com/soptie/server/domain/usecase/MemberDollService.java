package com.soptie.server.domain.usecase;

import com.soptie.server.domain.doll.DollType;

public interface MemberDollService {

	void createMemberDoll(Member member, DollType dollType, String name);

	void deleteMemberDoll(MemberDoll memberDoll);
}
