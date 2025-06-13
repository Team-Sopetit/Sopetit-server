package com.soptie.server.api.controller.routine.docs;

import java.security.Principal;
import java.util.LinkedHashSet;

import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.routine.dto.GetRoutinesByMemberResponse;
import com.soptie.server.api.controller.routine.dto.GetRoutinesByThemeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.ROUTINE_NAME, description = ApiTagConstants.ROUTINE_DESCRIPTION)
public interface RoutineApiDocs {

	@Operation(summary = "테마 목록별 데일리 루틴 목록 조회", description = "특정 테마 목록에 속하는 데일리 루틴 목록을 조회한다.")
	@ApiSuccessResponse
	SuccessResponse<GetRoutinesByThemeResponse> getRoutinesByThemeIds(
		@Parameter(
			name = "themeIds",
			description = "조회할 테마 id 목록",
			required = true,
			in = ParameterIn.QUERY
		) LinkedHashSet<Long> themeIds
	);

	@Operation(summary = "테마별 데일리 루틴 목록 조회", description = "특정 테마에 속하는 데일리 루틴 목록을 조회한다.")
	@ApiSuccessResponse
	SuccessResponse<GetRoutinesByMemberResponse> getRoutinesByThemeId(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "themeId",
			description = "조회하려는 루틴의 테마 id",
			required = true,
			in = ParameterIn.PATH
		) long themeId
	);
}
