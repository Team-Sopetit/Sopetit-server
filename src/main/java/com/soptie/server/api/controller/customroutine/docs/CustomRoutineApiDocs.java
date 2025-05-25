package com.soptie.server.api.controller.customroutine.docs;

import java.security.Principal;

import org.springframework.http.MediaType;

import com.soptie.server.api.controller.customroutine.dto.CustomRoutineRequest;
import com.soptie.server.api.controller.customroutine.dto.CustomRoutineResponse;
import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Custom Routine", description = "나만의 루틴 API")
public interface CustomRoutineApiDocs {

	@Operation(
		summary = "Create Custom Routine",
		description = "나만의 루틴 생성",
		responses = {
			@ApiResponse(responseCode = "201", description = "성공"),
			@ApiResponse(
				responseCode = "4xx",
				description = "클라이언트(요청) 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<CustomRoutineResponse> create(
		@Parameter(hidden = true) Principal principal,
		@RequestBody(
			description = "루틴 생성 요청 DTO",
			required = true,
			content = @Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = CustomRoutineRequest.class))
		) CustomRoutineRequest requestDto);

	@Operation(
		summary = "Update Custom Routine",
		description = "나만의 루틴 수정",
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
	SuccessResponse<CustomRoutineResponse> update(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "customRoutineId",
			description = "수정하려는 커스텀루틴(회원루틴) id",
			in = ParameterIn.PATH,
			required = true
		) long customRoutineId,
		@RequestBody(
			description = "루틴 수정 요청 DTO",
			required = true,
			content = @Content(
				mediaType = MediaType.APPLICATION_JSON_VALUE,
				schema = @Schema(implementation = CustomRoutineRequest.class))
		) CustomRoutineRequest requestDto);

	@Operation(
		summary = "Delete Custom Routine",
		description = "나만의 루틴 삭제",
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
	SuccessResponse<?> delete(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "customRoutineId",
			description = "삭제하려는 커스텀루틴(회원루틴) id",
			in = ParameterIn.PATH,
			required = true
		) long customRoutineId);
}
