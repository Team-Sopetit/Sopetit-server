package com.soptie.server.api.controller.dto.request.memberroutine;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberDailyRoutinesCreateRequest(
	@Schema(description = "추가하려는 루틴 id 목록", example = "[1, 2, 3]")
	List<Long> routineIds
) {
}
