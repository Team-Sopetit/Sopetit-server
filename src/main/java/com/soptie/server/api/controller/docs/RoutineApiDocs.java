package com.soptie.server.api.controller.docs;

import java.security.Principal;
import java.util.LinkedHashSet;

import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.routine.GetRoutinesByMemberResponse;
import com.soptie.server.api.controller.dto.response.routine.GetRoutinesByThemeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[Routine] 데일리 루틴 Version2", description = "데일리 루틴 API Version2")
public interface RoutineApiDocs {

	@Operation(
		summary = "테마 목록별 데일리 루틴 목록 조회",
		description = "특정 테마 목록에 속하는 데일리 루틴 목록을 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK Success"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
		}
	)
	SuccessResponse<GetRoutinesByThemeResponse> getRoutinesByThemeIds(
		@Parameter(
			name = "themeIds",
			description = "조회할 테마 id 목록",
			required = true,
			in = ParameterIn.QUERY
		) LinkedHashSet<Long> themeIds
	);

	@Operation(
		summary = "테마별 데일리 루틴 목록 조회",
		description = "특정 테마에 속하는 데일리 루틴 목록을 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK Success"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
		}
	)
	SuccessResponse<GetRoutinesByMemberResponse> getRoutinesByThemeId(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "themeId",
			description = "조회하려는 루틴의 테마 id",
			required = true,
			in = ParameterIn.PATH
		) long themeId
	);
}
