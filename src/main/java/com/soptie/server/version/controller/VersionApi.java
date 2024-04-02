package com.soptie.server.version.controller;

import org.springframework.http.ResponseEntity;

import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.version.dto.AppVersionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "versions", description = "버전 API")
public interface VersionApi {

	@Operation(
			summary = "",
			description = "",
			responses = {
					@ApiResponse(responseCode = "200", description = "성공"),
					@ApiResponse(
							responseCode = "500",
							description = "서버 내부 오류",
							content = @Content(schema = @Schema(implementation = ErrorResponse.class))
					)
			}
	)
	ResponseEntity<SuccessResponse<AppVersionResponse>> getClientAppVersion();
}
