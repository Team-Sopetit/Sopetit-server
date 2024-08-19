package com.soptie.server.api.controller.docs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.doll.DollImageResponse;
import com.soptie.server.domain.doll.DollType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "dolls", description = "애착 인형 API")
public interface DollApiDocs {

	@Operation(
		summary = "인형 이미지 불러오기",
		description = "인형 이미지를 불러온다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "400",
				description = "유효하지 않은 인형 타입",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	ResponseEntity<SuccessResponse<DollImageResponse>> getDollImages(
		@Parameter(
			name = "type",
			description = "인형 타입",
			in = ParameterIn.PATH,
			example = "BROWN"
		) @PathVariable DollType type
	);
}
