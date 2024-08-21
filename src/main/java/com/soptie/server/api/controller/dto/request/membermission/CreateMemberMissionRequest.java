package com.soptie.server.api.controller.dto.request.membermission;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateMemberMissionRequest(
	@Schema(description = "미션(도전루틴) id", example = "1")
	long subRoutineId
) {
}
