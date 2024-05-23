package com.soptie.server.routine.controller.v1.api;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v1.dto.response.DailyRoutineListByThemeGetResponse;
import com.soptie.server.routine.controller.v1.dto.response.DailyRoutineListByThemesGetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "daily routines V1", description = "데일리 루틴 API Version1")
public interface DailyRoutineApiV1 {

	@Operation(
			summary = "테마 목록별 데일리 루틴 목록 조회",
			description = "테마 목록 중 하나라도 해당되는 데일리 루틴 목록을 이름 오름차순으로 조회한다.",
			responses = {
					@ApiResponse(responseCode = "200", description = "성공"),
					@ApiResponse(
							responseCode = "4xx",
							description = "클라이언트(요청) 오류",
							content = @Content(schema = @Schema(implementation = ErrorResponse.class))
					),
					@ApiResponse(
							responseCode = "500",
							description = "서버 내부 오류",
							content = @Content(schema = @Schema(implementation = ErrorResponse.class))
					)
			}
	)
	ResponseEntity<SuccessResponse<DailyRoutineListByThemesGetResponse>> getRoutinesByThemes(
			@Parameter(
					name = "themes",
					description = "조회할 데일리 루틴 테마 id 목록",
					in = ParameterIn.QUERY,
					example = "1,2,3"
			) @RequestParam List<Long> themes
	);

	@Operation(
			summary = "테마별 데일리 루틴 목록 조회",
			description = "테마에 해당되는 데일리 루틴 목록을 이름 오름차순으로 조회한다.",
			responses = {
					@ApiResponse(responseCode = "200", description = "성공"),
					@ApiResponse(
							responseCode = "4xx",
							description = "클라이언트(요청) 오류",
							content = @Content(schema = @Schema(implementation = ErrorResponse.class))
					),
					@ApiResponse(
							responseCode = "500",
							description = "서버 내부 오류",
							content = @Content(schema = @Schema(implementation = ErrorResponse.class))
					)
			}
	)
	ResponseEntity<SuccessResponse<DailyRoutineListByThemeGetResponse>> getRoutinesByTheme(
			@Parameter(hidden = true) Principal principal,
			@Parameter(
					name = "themeId",
					description = "조회할 데일리 루틴 테마 id",
					in = ParameterIn.PATH,
					example = "1"
			) @PathVariable long themeId
	);
}
