package com.soptie.server.api.controller.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.challenge.GetChallengesByMemberResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[Challenge] 도전 루틴 API", description = "도전 루틴 API version2")
public interface ChallengeApiDocs {

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
	SuccessResponse<GetChallengesByMemberResponse> getChallengesByTheme(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "themeId",
			description = "조회할 도전 루틴 테마 id",
			in = ParameterIn.QUERY,
			example = "1"
		) @RequestParam long themeId
	);
}
