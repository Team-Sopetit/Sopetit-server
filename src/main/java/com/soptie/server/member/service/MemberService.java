package com.soptie.server.member.service;

import com.soptie.server.member.dto.CottonCountResponse;
import com.soptie.server.member.dto.MemberHomeInfoResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.CottonType;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.entity.SocialType;

public interface MemberService {

    void createMemberProfile(long memberId, MemberProfileRequest request);
    Member findBySocialTypeAndSocialId(SocialType socialType, String socialId);
    Member findMemberById(long memberId);
    CottonCountResponse giveCotton(long memberId, CottonType cottonType);
    MemberHomeInfoResponse getMemberHomeInfo(long memberId);
    Member findMemberByRefreshToken(String refreshToken);
    void deleteMember(Member member);
}
