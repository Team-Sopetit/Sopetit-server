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

@Tag(name = "MemberMissionApi_v2", description = "회원의 도전 루틴 API version2")
public interface MemberMissionApiV2Docs {

	@Operation(
		summary = "회원의 도전 루틴 조회",
		description = "회원의 도전 루틴을 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK success"),
			@ApiResponse(responseCode = "204", description = "NoContent success"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	ResponseEntity<?> getMemberMission(
		@Parameter(hidden = true) Principal principal
	);
}
