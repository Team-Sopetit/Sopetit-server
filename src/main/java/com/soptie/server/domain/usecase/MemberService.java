package com.soptie.server.domain.usecase;

import com.soptie.server.persistence.entity.Member;
import com.soptie.server.domain.member.CottonGiveServiceRequest;
import com.soptie.server.domain.member.MemberHomeInfoGetServiceRequest;
import com.soptie.server.domain.member.MemberProfileCreateServiceRequest;
import com.soptie.server.domain.member.MemberCottonCountGetServiceResponse;
import com.soptie.server.domain.member.MemberHomeInfoGetServiceResponse;

public interface MemberService {

	void createMemberProfile(MemberProfileCreateServiceRequest request);

	MemberCottonCountGetServiceResponse giveCotton(CottonGiveServiceRequest request);

	MemberHomeInfoGetServiceResponse getMemberHomeInfo(MemberHomeInfoGetServiceRequest request);

	void deleteMember(Member member);
}
