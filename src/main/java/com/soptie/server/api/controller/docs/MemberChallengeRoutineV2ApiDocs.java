package com.soptie.server.api.controller.docs;

import java.security.Principal;

import org.springframework.http.ResponseEntity;

import com.soptie.server.api.controller.dto.response.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "member challenge routines V2", description = "회원의 도전 루틴 API Version2")
public interface MemberChallengeRoutineV2ApiDocs {

	@Operation(
		summary = "회원의 도전 루틴 목록 조회",
		description = "회원의 도전 루틴을 테마와 함께 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "401",
				description = "유효하지 않은 토큰",
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
	ResponseEntity<?> acquire(
		@Parameter(hidden = true) Principal principal
	);
}
