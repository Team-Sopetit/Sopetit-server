package com.soptie.server.routine.controller.v2.docs;

import java.security.Principal;
import java.util.LinkedHashSet;

import org.springframework.http.ResponseEntity;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v2.dto.response.DailyRoutineListAcquireResponseV2;
import com.soptie.server.routine.controller.v2.dto.response.DailyRoutinesAcquireResponseV2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "DailyRoutineApiV2", description = "데일리 루틴 API (version2)")
public interface DailyRoutineControllerV2Docs {

	@Operation(
		summary = "데일리 루틴 목록 조회",
		description = "특정 테마 id 목록에 속하는 데일리 루틴 목록을 조회합니다."
	)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "OK Success",
			content = @Content(schema = @Schema(implementation = DailyRoutineListAcquireResponseV2.class)))})
	ResponseEntity<SuccessResponse<DailyRoutineListAcquireResponseV2>> acquireAllByThemes(
		@Parameter(
			name = "themeIds",
			description = "조회할 테마 id 목록",
			required = true,
			in = ParameterIn.QUERY
		) LinkedHashSet<Long> themeIds
	);

	@Operation(
		summary = "데일리 루틴 목록 조회",
		description = "특정 테마에 속하는 데일리 루틴 목록을 조회합니다. 회원이 가진 유무를 함께 확인할 수 있습니다."
	)
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200",
			description = "OK Success",
			content = @Content(schema = @Schema(implementation = DailyRoutinesAcquireResponseV2.class)))})
	SuccessResponse<DailyRoutinesAcquireResponseV2> acquireAllByThemeAndMember(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "themeId",
			description = "조회하려는 루틴의 테마 id",
			required = true,
			in = ParameterIn.PATH
		) long themeId
	);
}
