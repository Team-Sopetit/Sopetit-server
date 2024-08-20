package com.soptie.server.api.controller.docs;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.memberroutine.AchieveMemberRoutineResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MemberRoutineApi", description = "회원의 데일리 루틴 API")
public interface MemberRoutineApiDocs {

	@Operation(
		summary = "데일리 루틴 삭제",
		description = "회원의 데일리 루틴을 삭제한다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "성공",
				content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<?> deleteMemberRoutines(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "routines",
			description = "삭제할 회원의 데일리 루틴 id 목록",
			in = ParameterIn.QUERY,
			example = "1,2,3"
		) @RequestParam List<Long> routines
	);

	@Operation(
		summary = "데일리 루틴 달성",
		description = "회원의 데일리 루틴을 달성한다.",
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
	SuccessResponse<AchieveMemberRoutineResponse> achieveMemberRoutine(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "routineId",
			description = "달성한 회원의 데일리 루틴 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable long routineId
	);
}
