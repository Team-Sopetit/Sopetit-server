package com.soptie.server.memberroutine.controller.v1.docs;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.memberroutine.controller.v1.dto.request.MemberDailyRoutineCreateRequest;
import com.soptie.server.memberroutine.controller.v1.dto.response.MemberDailyRoutineAchieveResponse;
import com.soptie.server.memberroutine.controller.v1.dto.response.MemberDailyRoutineCreateResponse;
import com.soptie.server.memberroutine.controller.v1.dto.response.MemberDailyRoutineListAcquireResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "member daily routines V1", description = "회원의 데일리 루틴 API Version1")
public interface MemberDailyRoutineControllerDocs {

	@Operation(
		summary = "데일리 루틴 추가",
		description = "회원의 데일리 루틴을 추가한다.",
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
	ResponseEntity<SuccessResponse<MemberDailyRoutineCreateResponse>> createMemberDailyRoutine(
		@Parameter(hidden = true) Principal principal,
		@RequestBody MemberDailyRoutineCreateRequest request
	);

	@Operation(
		summary = "데일리 루틴 삭제",
		description = "회원의 데일리 루틴을 삭제한다.",
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
	ResponseEntity<BaseResponse> deleteMemberDailyRoutines(
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
	ResponseEntity<SuccessResponse<MemberDailyRoutineAchieveResponse>> achieveMemberDailyRoutine(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "routineId",
			description = "달성한 회원의 데일리 루틴 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable long routineId
	);

	@Operation(
		summary = "회원의 데일리 루틴 목록 조회",
		description = "회원의 데일리 루틴을 달성 여부, 달성 횟수, 루틴 이름 순으로 정렬된 목록으로 조회한다.",
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
	ResponseEntity<SuccessResponse<MemberDailyRoutineListAcquireResponse>> getMemberDailyRoutines(
		@Parameter(hidden = true) Principal principal
	);
}
