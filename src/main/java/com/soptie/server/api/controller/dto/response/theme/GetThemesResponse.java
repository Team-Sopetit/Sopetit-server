package com.soptie.server.api.controller.dto.response.theme;

import java.util.List;

import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetThemesResponse(
	@Schema(description = "테마 정보 목록")
	@NotNull List<ThemeResponse> themes
) {

	public static GetThemesResponse of(List<Theme> themes) {
		return GetThemesResponse.builder()
			.themes(themes.stream().map(ThemeResponse::of).toList())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record ThemeResponse(
		@Schema(description = "테마 id", example = "1")
		long themeId,
		@Schema(description = "테마 이름", example = "관계 쌓기")
		@NotNull String title,
		@Schema(description = "테마 코멘트", example = "사람들과 어울리는")
		@NotNull String subTitle,
		@Schema(description = "테마 설명", example = "행복은 흔히 관계에서 온다고 해 ...더보기")
		@NotNull String description
	) {

		private static ThemeResponse of(Theme theme) {
			return ThemeResponse.builder()
				.themeId(theme.getId())
				.title(theme.getName())
				.subTitle(theme.getComment())
				.description(theme.getDescription())
				.build();
		}
	}
}
