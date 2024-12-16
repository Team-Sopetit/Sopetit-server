package com.soptie.server.api.controller.dto.response.membermission;

import com.soptie.server.domain.membermission.MemberChallenge;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CreateMemberChallengeResponse(
	@Schema(description = "추가한 회원의 챌린지 id", example = "1")
	long memberChallengeId
) {

	public static CreateMemberChallengeResponse of(MemberChallenge memberChallenge) {
		return CreateMemberChallengeResponse.builder()
			.memberChallengeId(memberChallenge.getId())
			.build();
	}
}
