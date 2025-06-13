package com.soptie.server.api.controller.memberroutine.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.soptie.server.domain.memberroutine.MemberRoutine;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MemberRoutineResponse(
	@Schema(description = "회원 루틴 id", example = "1")
	long routineId,

	@Schema(description = "소프티 루틴 id", example = "1")
	Long originRoutineId,

	@Schema(description = "루틴 내용", example = "밥 먹기")
	@NotNull
	String content,

	@Schema(description = "달성 횟수", example = "10")
	int achieveCount,

	@Schema(description = "달성 여부", example = "true")
	boolean isAchieve,

	@Schema(description = "알림 시간", example = "20:00:00")
	LocalTime alarmTime
) {

	public static MemberRoutineResponse from(MemberRoutine memberRoutine) {
		return MemberRoutineResponse.builder()
			.routineId(memberRoutine.getId())
			.originRoutineId(memberRoutine.getRoutineId())
			.content(memberRoutine.getContent())
			.achieveCount(memberRoutine.getAchievementCount())
			.isAchieve(memberRoutine.isAchieved())
			.alarmTime(memberRoutine.getAlarmTime())
			.build();
	}
}
