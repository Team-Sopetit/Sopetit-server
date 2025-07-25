package com.soptie.server.api.controller.achievement.docs;

import java.security.Principal;

import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.achievement.dto.AchievedThemeResponse;
import com.soptie.server.api.controller.achievement.dto.AchievedThemesResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.ACHIEVEMENT_NAME, description = ApiTagConstants.ACHIEVEMENT_DESCRIPTION)
public interface AchievementApiV3Docs {

	@Operation(summary = "테마 달성도 조회", description = "테마의 달성도 목록을 조회한다.")
	@ApiSuccessResponse
	SuccessResponse<AchievedThemesResponse> getAchievementByThemes(
		@Parameter(hidden = true)
		Principal principal
	);

	@Operation(summary = "루틴 달성도 조회", description = "특정 테마의 루틴의 달성도 정보를 조회한다.")
	@ApiSuccessResponse
	SuccessResponse<AchievedThemeResponse> getAchievementTheme(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "themeId",
			description = "조회할 테마 id",
			in = ParameterIn.PATH,
			required = true,
			example = "1") long themeId
	);
}
