package com.soptie.server.api.controller.dto.response.member;

import java.util.List;

import com.soptie.server.domain.doll.DollType;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memberdoll.MemberDoll;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetHomeInfoResponse(
	@Schema(description = "인형 이름", example = "브라운")
	@NotNull String name,
	@Schema(description = "인형 타입", example = "BROWN")
	@NotNull DollType dollType,
	@Schema(description = "일반 솜뭉치 개수", example = "10")
	int dailyCottonCount,
	@Schema(description = "무지개 솜뭉치 개수", example = "10")
	int happinessCottonCount,
	@Schema(description = "대화 내용 목록", example = "[\"안녕?\", \"반가워~\"]")
	@NotNull List<String> conversations
) {

	public static GetHomeInfoResponse of(Member member, MemberDoll memberDoll, List<String> conversations) {
		return GetHomeInfoResponse.builder()
			.name(memberDoll.getName())
			.dollType(memberDoll.getDollType())
			.dailyCottonCount(member.getCottonInfo().getBasicCottonCount())
			.happinessCottonCount(member.getCottonInfo().getRainbowCottonCount())
			.conversations(conversations)
			.build();
	}
}
