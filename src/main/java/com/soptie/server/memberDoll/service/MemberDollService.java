package com.soptie.server.memberDoll.service;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberDoll.entity.MemberDoll;

public interface MemberDollService {

    void createMemberDoll(Member member, DollType dollType, String name);
    void deleteMemberDoll(MemberDoll memberDoll);
}
