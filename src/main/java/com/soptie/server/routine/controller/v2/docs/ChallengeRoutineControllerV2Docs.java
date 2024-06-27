package com.soptie.server.routine.controller.v2.docs;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v2.dto.response.ChallengeRoutineListAcquireResponseV2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "challenge routines V2", description = "도전 루틴 API Version2")
public interface ChallengeRoutineControllerV2Docs {

	@Operation(
		summary = "테마별 도전 루틴 목록 조회",
		description = "테마에 해당되는 도전 루틴 목록을 조회한다.",
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
	ResponseEntity<SuccessResponse<ChallengeRoutineListAcquireResponseV2>> acquireAllByTheme(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "themeId",
			description = "조회할 도전 루틴 테마 id",
			in = ParameterIn.QUERY,
			example = "1"
		) @RequestParam Long themeId
	);
}
