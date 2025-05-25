package com.soptie.server.api.controller.theme.dto;

import java.util.List;

import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetThemesResponse(
	@Schema(description = "테마 정보 목록")
	@NotNull
	List<ThemeResponse> themes
) {

	public static GetThemesResponse from(List<Theme> themes) {
		return GetThemesResponse.builder()
			.themes(themes.stream().map(ThemeResponse::from).toList())
			.build();
	}
}
