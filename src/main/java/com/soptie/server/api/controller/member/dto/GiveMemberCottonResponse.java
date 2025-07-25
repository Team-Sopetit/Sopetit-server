package com.soptie.server.api.controller.member.dto;

import com.soptie.server.domain.member.CottonType;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.member.MemberCotton;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GiveMemberCottonResponse(
	@Schema(description = "회원의 솜뭉치 개수", example = "5")
	int cottonCount
) {

	public static GiveMemberCottonResponse of(Member member, CottonType cottonType) {
		return GiveMemberCottonResponse.builder()
			.cottonCount(getCottonCount(member.getCottonInfo(), cottonType))
			.build();
	}

	private static int getCottonCount(MemberCotton cotton, CottonType cottonType) {
		return switch (cottonType) {
			case DAILY -> cotton.getBasicCottonCount();
			case HAPPINESS -> cotton.getRainbowCottonCount();
		};
	}
}
