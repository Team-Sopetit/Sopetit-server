package com.soptie.server.api.controller.docs;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.soptie.server.api.controller.dto.request.membermission.CreateMemberChallengeRequest;
import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.membermission.CreateMemberChallengeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[MemberChallenge] 회원 챌린지 API", description = "회원 챌린지 API version2")
public interface MemberChallengeApiDocs {

	@Operation(
		summary = "회원 챌린지 조회",
		description = "회원의 챌린지를 조회한다.",
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
	ResponseEntity<?> getMemberChallenge(
		@Parameter(hidden = true) Principal principal
	);

	@Operation(
		summary = "회원 챌린지 추가",
		description = "회원의 챌린지를 추가한다.",
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
	SuccessResponse<CreateMemberChallengeResponse> createMemberChallenge(
		@Parameter(hidden = true) Principal principal,
		@RequestBody CreateMemberChallengeRequest request
	);

	@Operation(
		summary = "회원 챌린지 삭제",
		description = "회원의 챌린지를 삭제한다.",
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
	SuccessResponse<?> deleteMemberChallenge(
		@Parameter(hidden = true) Principal principal
	);

	@Operation(
		summary = "회원 챌린지 달성",
		description = "회원의 챌린지를 달성한다.",
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
	SuccessResponse<?> achieveMemberChallenge(
		@Parameter(hidden = true) Principal principal
	);

	@Operation(
		summary = "챌린지 기록 삭제",
		description = "달성한 챌린지 기록을 삭제합니다.",
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
	SuccessResponse<?> deleteHistory(
		@Parameter(
			name = "historyId",
			description = "삭제하려는 이력 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable long historyId
	);
}
