package com.soptie.server.domain.usecase;

import com.soptie.server.persistence.entity.DollType;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.entity.MemberDoll;

public interface MemberDollService {

	void createMemberDoll(Member member, DollType dollType, String name);

	void deleteMemberDoll(MemberDoll memberDoll);
}
