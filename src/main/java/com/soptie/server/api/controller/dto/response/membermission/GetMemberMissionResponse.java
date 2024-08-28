package com.soptie.server.api.controller.dto.response.membermission;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.Mission;
import com.soptie.server.domain.membermission.MemberMission;
import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetMemberMissionResponse(
	@Schema(description = "미션(도전루틴) id", example = "1")
	long routineId,
	@Schema(description = "테마 id", example = "1")
	long themeId,
	@Schema(description = "테마 이름", example = "관계 쌓기")
	@NotNull String themeName,
	@Schema(description = "도전 이름", example = "오래가는 인연을 위한 준비")
	@NotNull String title,
	@Schema(description = "미션 내용", example = "일어나면 5분 안에 이불 개기")
	@NotNull String content,
	@Schema(description = "미션 상세 내용", example = "평소에 바빠서 ...더보기")
	@NotNull String detailContent,
	@Schema(description = "장소", example = "침대")
	@NotNull String place,
	@Schema(description = "소요시간", example = "3분")
	@NotNull String timeTaken
) {

	public static GetMemberMissionResponse of(
		MemberMission memberMission, Theme theme, Challenge challenge, Mission mission
	) {
		return GetMemberMissionResponse.builder()
			.routineId(memberMission.getId())
			.themeId(theme.getId())
			.themeName(theme.getName())
			.title(challenge.getName())
			.content(mission.getContent())
			.detailContent(mission.getDescription())
			.place(mission.getPlace())
			.timeTaken(mission.getRequiredTime())
			.build();
	}
}
