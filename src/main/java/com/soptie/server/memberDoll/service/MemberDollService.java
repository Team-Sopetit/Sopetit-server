package com.soptie.server.memberDoll.service;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.entity.Member;

public interface MemberDollService {

    void createMemberDoll(Member member, DollType dollType, String name);
}
