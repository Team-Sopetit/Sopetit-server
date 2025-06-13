package com.soptie.server.api.controller.member.dto;

import java.util.List;

import com.soptie.server.domain.doll.DollType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CreateProfileRequest(
	@Schema(description = "인형 타입", example = "BROWN")
	@NotNull DollType dollType,
	@Schema(description = "인형 이름", example = "브라운")
	@NotNull String name,
	@Schema(description = "추가하려는 데일리 루틴 id 목록", example = "[1, 2, 3]")
	@NotNull List<Long> routines
) {
}
