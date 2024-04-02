package com.soptie.server.memberRoutine.controller.daily;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.memberRoutine.dto.AchievedMemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutinesResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "member daily routines", description = "회원의 데일리 루틴 API")
public interface MemberDailyRoutineApi {

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
	ResponseEntity<SuccessResponse<MemberDailyRoutineResponse>> createMemberDailyRoutine(
			@Parameter(hidden = true) Principal principal,
			@RequestBody MemberDailyRoutineRequest request
	);

	@Operation(
			summary = "",
			description = "",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "성공",
							content = @Content(schema = @Schema(implementation = SuccessResponse.class))
					),
					@ApiResponse(
							responseCode = "500",
							description = "서버 내부 오류",
							content = @Content(schema = @Schema(implementation = ErrorResponse.class))
					)
			}
	)
	ResponseEntity<BaseResponse> deleteMemberDailyRoutines(
			@Parameter(hidden = true) Principal principal,
			@RequestParam List<Long> routines
	);

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
	ResponseEntity<SuccessResponse<AchievedMemberDailyRoutineResponse>> achieveMemberDailyRoutine(
			@Parameter(hidden = true) Principal principal,
			@PathVariable Long routineId
	);

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
	ResponseEntity<SuccessResponse<MemberDailyRoutinesResponse>> getMemberDailyRoutines(
			@Parameter(hidden = true) Principal principal
	);
}
