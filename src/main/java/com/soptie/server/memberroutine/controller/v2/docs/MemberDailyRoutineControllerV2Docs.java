package com.soptie.server.memberroutine.controller.v2.docs;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.memberroutine.controller.v1.dto.request.MemberDailyRoutinesCreateRequest;
import com.soptie.server.memberroutine.controller.v2.dto.response.MemberDailyRoutineListAcquireResponseV2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "member daily routines V2", description = "회원의 데일리 루틴 API Version2")
public interface MemberDailyRoutineControllerV2Docs {

	@Operation(
		summary = "회원의 데일리 루틴 목록 조회",
		description = "회원의 데일리 루틴을 무지개 순으로 정렬된 테마와 사전순으로 정렬된 루틴 목록으로 조회한다.",
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
	ResponseEntity<SuccessResponse<MemberDailyRoutineListAcquireResponseV2>> acquireAll(
		@Parameter(hidden = true) Principal principal
	);

	@Operation(
		summary = "데일리 루틴 다중 추가",
		description = "회원의 데일리 루틴을 다중으로 추가한다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "성공",
				content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
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
	ResponseEntity<BaseResponse> createMemberDailyRoutines(
		@Parameter(hidden = true) Principal principal,
		@RequestBody MemberDailyRoutinesCreateRequest request
	);
}
