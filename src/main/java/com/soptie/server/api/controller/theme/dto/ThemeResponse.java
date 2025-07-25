package com.soptie.server.api.controller.theme.dto;

import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ThemeResponse(
	@Schema(description = "테마 id", example = "1")
	long themeId,

	@Schema(description = "테마 이름", example = "관계 쌓기")
	@NotNull
	String title,

	@Schema(description = "테마 코멘트", example = "사람들과 어울리는")
	@NotNull
	String subTitle,

	@Schema(description = "테마 설명", example = "행복은 흔히 관계에서 온다고 해 ...더보기")
	@NotNull
	String description
) {

	public static ThemeResponse from(Theme theme) {
		return ThemeResponse.builder()
			.themeId(theme.getId())
			.title(theme.getName())
			.subTitle(theme.getComment())
			.description(theme.getDescription())
			.build();
	}
}
