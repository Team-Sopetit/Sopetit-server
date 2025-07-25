package com.soptie.server.api.controller.theme.docs;

import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.theme.dto.GetThemesResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.THEME_NAME, description = ApiTagConstants.THEME_DESCRIPTION)
public interface ThemeApiV2Docs {

	@Operation(summary = "Get Themes", description = "일반 테마 목록을 조회한다.")
	@ApiSuccessResponse
	SuccessResponse<GetThemesResponse> getAll();
}
