package com.soptie.server.api.controller.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestBody;

import com.soptie.server.api.controller.dto.request.memberroutine.CreateMemberRoutinesRequest;
import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.memberroutine.CreateMemberRoutinesResponse;
import com.soptie.server.api.controller.dto.response.memberroutine.GetMemberRoutinesResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MemberRoutineApi_v2", description = "회원 데일리 루틴 API version2")
public interface MemberRoutineApiV2Docs {

	@Operation(
		summary = "데일리 루틴 다중 추가",
		description = "회원의 데일리 루틴을 다중으로 추가한다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "CREATED Success"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<CreateMemberRoutinesResponse> createMemberRoutines(
		@Parameter(hidden = true) Principal principal,
		@RequestBody CreateMemberRoutinesRequest request
	);

	@Operation(
		summary = "회원의 데일리 루틴 목록 조회",
		description = "회원의 데일리 루틴 목록을 조회한다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK Success"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<GetMemberRoutinesResponse> getMemberRoutines(
		@Parameter(hidden = true) Principal principal
	);
}
