package com.soptie.server.domain.member;

import static lombok.AccessLevel.*;

import java.util.List;

import com.soptie.server.persistence.entity.DollType;
import com.soptie.server.persistence.entity.Member;

import lombok.Builder;
import lombok.NonNull;

@SuppressWarnings("checkstyle:LineLength")
@Builder(access = PRIVATE)
public record MemberHomeInfoGetServiceResponse(
	@NonNull String name,
	@NonNull DollType dollType,
	@NonNull String frameImageUrl,
	int dailyCottonCount,
	int happinessCottonCount,
	@NonNull List<String> conversations
) {

	public static MemberHomeInfoGetServiceResponse of(Member member, List<String> conversations) {
		return MemberHomeInfoGetServiceResponse.builder()
			.name(member.getMemberDoll().getName())
			.dollType(member.getMemberDoll().getDoll().getDollType())
			.frameImageUrl(member.getMemberDoll().getDoll().getImageLinks().getFrameImageUrl())
			.dailyCottonCount(member.getCottonInfo().getDailyCottonCount())
			.happinessCottonCount(member.getCottonInfo().getHappinessCottonCount())
			.conversations(conversations)
			.build();
	}
}
