package com.soptie.server.api.controller.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.PathVariable;

import com.soptie.server.api.controller.dto.request.membermission.CreateMemberMissionRequest;
import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.membermission.CreateMemberMissionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MemberMissionApi", description = "회원의 도전 루틴 API")
public interface MemberMissionApiDocs {

	@Operation(
		summary = "미션(도전 루틴) 추가",
		description = "회원의 미션을 추가한다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "Created success"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<CreateMemberMissionResponse> createMemberMission(
		@Parameter(hidden = true) Principal principal,
		@RequestBody CreateMemberMissionRequest request
	);

	@Operation(
		summary = "미션 삭제",
		description = "회원의 미션을 삭제한다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "OK success",
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
	SuccessResponse<?> deleteMemberMission(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "routineId",
			description = "삭제할 회원의 미션 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable long routineId
	);

	@Operation(
		summary = "미션 달성",
		description = "회원의 미션을 달성한다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "OK success",
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
	SuccessResponse<?> achieveMemberMission(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "routineId",
			description = "달성할 회원의 미션 id",
			in = ParameterIn.PATH,
			example = "1"
		)
		@PathVariable long routineId
	);
}
