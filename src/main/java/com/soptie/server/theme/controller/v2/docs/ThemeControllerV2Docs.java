package com.soptie.server.theme.controller.v2.docs;

import org.springframework.http.ResponseEntity;

import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.theme.controller.v2.dto.response.ThemeListAcquireResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "themes-v2", description = "테마 API Version2")
public interface ThemeControllerV2Docs {

	@Operation(
		summary = "테마 목록 조회",
		description = "일반 테마 목록을 조회한다. (전문가 테마 미포함)",
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
	ResponseEntity<SuccessResponse<ThemeListAcquireResponse>> acquireAllInBasic();
}
