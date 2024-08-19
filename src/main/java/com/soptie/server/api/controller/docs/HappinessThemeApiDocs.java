package com.soptie.server.api.controller.docs;

import org.springframework.http.ResponseEntity;

import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.theme.HappinessThemeListGetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "happiness theme V1", description = "행복 테마 API Version1")
public interface HappinessThemeApiDocs {

	@Operation(
		summary = "행복 루틴 테마 목록 조회",
		description = "행복 루틴 테마 전체를 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
	ResponseEntity<SuccessResponse<HappinessThemeListGetResponse>> acquireAllInBasic();

}
