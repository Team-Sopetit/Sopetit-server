package com.soptie.server.doll.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.doll.dto.DollImageResponse;
import com.soptie.server.doll.entity.DollType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "dolls", description = "애착 인형 API")
public interface DollApi {

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
	ResponseEntity<SuccessResponse<DollImageResponse>> getDollImages(
			@PathVariable DollType type
	);
}
