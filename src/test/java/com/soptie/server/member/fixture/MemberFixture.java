package com.soptie.server.member.fixture;

import com.soptie.server.member.entity.Cotton;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberDoll.entity.MemberDoll;

public class MemberFixture {

    public static Member getMember() {
        return new Member(1L, getMemberDoll());
    }

    public static MemberDoll getMemberDoll() {
        return new MemberDoll(1L);
    }

}
