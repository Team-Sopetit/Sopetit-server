package com.soptie.server.member.service;

import com.soptie.server.member.dto.MemberProfileRequest;

public interface MemberService {

    void createMemberProfile(Long memberId, MemberProfileRequest request);
}
