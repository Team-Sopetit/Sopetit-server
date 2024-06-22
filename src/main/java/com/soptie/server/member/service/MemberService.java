package com.soptie.server.member.service;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.service.dto.request.CottonGiveServiceRequest;
import com.soptie.server.member.service.dto.request.MemberHomeInfoGetServiceRequest;
import com.soptie.server.member.service.dto.request.MemberProfileCreateServiceRequest;
import com.soptie.server.member.service.dto.response.MemberCottonCountGetServiceResponse;
import com.soptie.server.member.service.dto.response.MemberHomeInfoGetServiceResponse;

public interface MemberService {

	void createMemberProfile(MemberProfileCreateServiceRequest request);

	MemberCottonCountGetServiceResponse giveCotton(CottonGiveServiceRequest request);

	MemberHomeInfoGetServiceResponse getMemberHomeInfo(MemberHomeInfoGetServiceRequest request);

	void deleteMember(Member member);
}
