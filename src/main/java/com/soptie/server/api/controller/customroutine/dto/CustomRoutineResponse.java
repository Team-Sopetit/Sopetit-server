package com.soptie.server.api.controller.customroutine.dto;

import java.time.LocalTime;

import com.soptie.server.domain.memberroutine.MemberRoutine;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CustomRoutineResponse(

	@Schema(description = "생성된 커스텀 루틴 id", example = "1")
	long id,

	@Schema(description = "루틴 내용", example = "비타민 먹기")
	@NotNull
	String content,

	@Schema(description = "테마 id", example = "1")
	long themeId,

	@Schema(description = "알림 시간(HH:mm:ss)", example = "20:00:00")
	LocalTime alarmTime
) {

	public static CustomRoutineResponse of(MemberRoutine memberRoutine) {
		return CustomRoutineResponse.builder()
			.id(memberRoutine.getId())
			.content(memberRoutine.getContent())
			.themeId(memberRoutine.getThemeId())
			.alarmTime(memberRoutine.getAlarmTime())
			.build();
	}
}
