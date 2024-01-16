package com.soptie.server.member.service;

import com.soptie.server.member.dto.CottonCountResponse;
import com.soptie.server.member.dto.MemberHomeInfoResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.CottonType;

public interface MemberService {

    void createMemberProfile(Long memberId, MemberProfileRequest request);
    CottonCountResponse giveCotton(Long memberId, CottonType cottonType);
    MemberHomeInfoResponse getMemberHomeInfo(Long memberId);
}
