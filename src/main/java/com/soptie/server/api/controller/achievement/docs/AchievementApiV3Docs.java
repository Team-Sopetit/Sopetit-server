package com.soptie.server.api.controller.achievement.docs;

import java.security.Principal;

import com.soptie.server.api.controller.achievement.dto.AchievedThemeResponse;
import com.soptie.server.api.controller.achievement.dto.AchievedThemesResponse;
import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Achievement V3", description = "달성도 api")
public interface AchievementApiV3Docs {

	@Operation(
		summary = "Get Achievement by Themes",
		description = "테마 달성도 목록을 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<AchievedThemesResponse> getAchievementByThemes(
		@Parameter(hidden = true) Principal principal
	);

	@Operation(
		summary = "테마별 루틴 달성도 조회",
		description = "테마 내 달성한 루틴 정보를 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<AchievedThemeResponse> getAchievementTheme(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "themeId",
			description = "조회할 테마 id",
			in = ParameterIn.PATH,
			required = true,
			example = "1"
		) long themeId
	);
}
