package com.soptie.server.api.controller.dto.request.memo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CreateMemoRequest(
	@Schema(description = "달성 날짜", example = "2024-11-30")
	@NotNull LocalDate achievedDate,
	@Schema(description = "메모 내용", example = "좋은 루틴이구만.")
	@NotNull String content
) {
}
