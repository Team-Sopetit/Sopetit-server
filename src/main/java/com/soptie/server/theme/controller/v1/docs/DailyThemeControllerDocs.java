package com.soptie.server.theme.controller.v1.docs;

import org.springframework.http.ResponseEntity;

import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.theme.controller.v1.dto.response.DailyThemeListGetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "daily themes V1", description = "데일리 테마 API Version1")
public interface DailyThemeControllerDocs {

	@Operation(
		summary = "데일리 루틴 테마 목록 조회",
		description = "데일리 루틴 테마 전체를 조회한다.",
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
	ResponseEntity<SuccessResponse<DailyThemeListGetResponse>> acquireAllInBasic();
}
