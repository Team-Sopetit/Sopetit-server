package com.soptie.server.api.controller.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.soptie.server.api.controller.dto.request.memo.CreateMemoRequest;
import com.soptie.server.api.controller.dto.request.memo.ModifyMemoRequest;
import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.memo.CreateMemoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "MemoApi_v3", description = "메모 API version3")
public interface MemoApiDocs {

	@Operation(
		summary = "메모 생성",
		description = "메모를 생성한다.",
		responses = {
			@ApiResponse(
				responseCode = "201",
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
	SuccessResponse<CreateMemoResponse> createMemo(
		@Parameter(hidden = true) Principal principal,
		@RequestBody CreateMemoRequest request
	);

	@Operation(
		summary = "메모 수정",
		description = "메모를 수정한다.",
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
	SuccessResponse<?> modifyMemo(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "memoId",
			description = "수정할 메모 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable final long memoId,
		@RequestBody ModifyMemoRequest request
	);

	@Operation(
		summary = "메모 삭제",
		description = "메모를 삭제한다.",
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
	SuccessResponse<?> deleteMemo(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "memoId",
			description = "삭제할 메모 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable final long memoId
	);
}
