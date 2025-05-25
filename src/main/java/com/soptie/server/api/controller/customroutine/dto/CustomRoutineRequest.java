package com.soptie.server.api.controller.customroutine.dto;

import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CustomRoutineRequest(
	@Schema(description = "루틴 내용", example = "비타민 먹기")
	@NotNull
	String content,

	@Schema(description = "테마 id", example = "1")
	long themeId,

	@Schema(description = "알림 시간(HH:mm:ss)", example = "20:00:00")
	LocalTime alarmTime
) {
}
