package com.soptie.server.api.controller.dto.request.membermission;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateMemberChallengeRequest(
	@Schema(description = "추가하려는 챌린지 id", example = "1")
	long challengeId
) {
}
