package com.soptie.server.api.controller.dto.response.membermission;

import com.soptie.server.domain.membermission.MemberMission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CreateMemberMissionResponse(
	@Schema(description = "추가한 회원의 미션 id", example = "1")
	long routineId
) {

	public static CreateMemberMissionResponse of(MemberMission memberMission) {
		return CreateMemberMissionResponse.builder()
			.routineId(memberMission.getId())
			.build();
	}
}
