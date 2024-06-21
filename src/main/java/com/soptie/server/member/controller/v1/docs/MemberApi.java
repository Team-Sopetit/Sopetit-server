package com.soptie.server.member.controller.v1.docs;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.member.controller.v1.dto.request.MemberProfileCreateRequest;
import com.soptie.server.member.controller.v1.dto.response.MemberCottonCountGetResponse;
import com.soptie.server.member.controller.v1.dto.response.MemberHomeInfoGetResponse;
import com.soptie.server.member.entity.CottonType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "members", description = "회원 API")
public interface MemberApi {

	@Operation(
		summary = "멤버 프로필 생성",
		description = "멤버 프로필을 생성한다.",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "성공",
				content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
			@ApiResponse(
				responseCode = "400",
				description = "유효하지 않은 인형 타입",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "400",
				description = "조건에 맞지 않는 이름",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "유효하지 않은 회원",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "409",
				description = "이미 존재하는 프로필",
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
	ResponseEntity<BaseResponse> createMemberProfile(
		@Parameter(hidden = true) Principal principal,
		@RequestBody MemberProfileCreateRequest request
	);

	@Operation(
		summary = "솜뭉치 주기",
		description = "솜뭉치를 준다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "400",
				description = "솜뭉치 부족",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "유효하지 않은 회원",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "인형을 가지고 있지 않은 회원",
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
	ResponseEntity<SuccessResponse<MemberCottonCountGetResponse>> giveCotton(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "cottonType",
			description = "솜뭉치 타입",
			in = ParameterIn.PATH,
			example = "DAILY"
		) @PathVariable CottonType cottonType
	);

	@Operation(
		summary = "홈 화면 불러오기",
		description = "홈 화면을 불러온다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "404",
				description = "유효하지 않은 회원",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(
				responseCode = "404",
				description = "인형을 가지고 있지 않은 회원",
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
	ResponseEntity<SuccessResponse<MemberHomeInfoGetResponse>> getMemberHomeInfo(
		@Parameter(hidden = true) Principal principal
	);
}
