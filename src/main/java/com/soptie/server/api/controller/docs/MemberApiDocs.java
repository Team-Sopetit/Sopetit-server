package com.soptie.server.api.controller.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.soptie.server.api.controller.dto.request.member.CreateProfileRequest;
import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.member.GetHomeInfoResponse;
import com.soptie.server.api.controller.dto.response.member.GiveMemberCottonResponse;
import com.soptie.server.api.controller.dto.response.member.MemberProfileResponse;
import com.soptie.server.domain.member.CottonType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[Member] 회원 API", description = "회원 API")
public interface MemberApiDocs {

	@Operation(
		summary = "회원 프로필 생성",
		description = "회원의 프로필을 생성한다.",
		responses = {
			@ApiResponse(
				responseCode = "201",
				description = "CREATED success",
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
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<?> createMemberProfile(
		@Parameter(hidden = true) Principal principal,
		@RequestBody CreateProfileRequest request
	);

	@Operation(
		summary = "솜뭉치 주기",
		description = "솜뭉치를 준다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "OK success"),
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
	SuccessResponse<GiveMemberCottonResponse> giveCotton(
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
	SuccessResponse<GetHomeInfoResponse> getMemberHomeInfo(
		@Parameter(hidden = true) Principal principal
	);

	@Operation(
		summary = "회원 정보 조회",
		description = "회원의 정보를 조회합니다.",
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
	SuccessResponse<MemberProfileResponse> getMemberProfile(
		@Parameter(hidden = true) Principal principal
	);
}
