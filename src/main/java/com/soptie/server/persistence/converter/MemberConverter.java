package com.soptie.server.persistence.converter;

import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.member.MemberCotton;
import com.soptie.server.domain.member.Social;
import com.soptie.server.persistence.entity.MemberEntity;

public class MemberConverter {

	public static Member convert(MemberEntity memberEntity) {
		return Member.builder()
			.id(memberEntity.getId())
			.socialInfo(convertToSocial(memberEntity))
			.refreshToken(memberEntity.getRefreshToken())
			.cottonInfo(convertToMemberCotton(memberEntity))
			.createdAt(memberEntity.getCreatedAt())
			.lastVisitDateTime(memberEntity.getLastVisitDateTime())
			.build();
	}

	private static Social convertToSocial(MemberEntity memberEntity) {
		return Social.builder()
			.socialType(memberEntity.getSocialType())
			.socialId(memberEntity.getSocialId())
			.build();
	}

	private static MemberCotton convertToMemberCotton(MemberEntity memberEntity) {
		return MemberCotton.builder()
			.basicCottonCount(memberEntity.getBasicCottonCount())
			.rainbowCottonCount(memberEntity.getRainbowCottonCount())
			.build();
	}
}
