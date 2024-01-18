package com.soptie.server.member.service;

import com.soptie.server.member.dto.CottonCountResponse;
import com.soptie.server.member.dto.MemberHomeInfoResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.CottonType;
import com.soptie.server.member.entity.Member;

public interface MemberService {

    void createMemberProfile(long memberId, MemberProfileRequest request);
    CottonCountResponse giveCotton(long memberId, CottonType cottonType);
    MemberHomeInfoResponse getMemberHomeInfo(long memberId);
    Member findMemberByRefreshToken(String refreshToken);
}
