package com.soptie.server.api.controller.member.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.soptie.server.api.common.annotation.ApiCreatedResponse;
import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.member.dto.CreateProfileRequest;
import com.soptie.server.api.controller.member.dto.GetHomeInfoResponse;
import com.soptie.server.api.controller.member.dto.GiveMemberCottonResponse;
import com.soptie.server.api.controller.member.dto.MemberProfileResponse;
import com.soptie.server.domain.member.CottonType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.MEMBER_NAME, description = ApiTagConstants.MEMBER_DESCRIPTION)
public interface MemberApiDocs {

	@Operation(summary = "회원 프로필 생성", description = "회원의 프로필을 생성한다.")
	@ApiCreatedResponse
	SuccessResponse<?> createMemberProfile(
		@Parameter(hidden = true)
		Principal principal,

		@RequestBody
		CreateProfileRequest request
	);

	@Operation(summary = "솜뭉치 주기", description = "솜뭉치를 준다.")
	@ApiSuccessResponse
	SuccessResponse<GiveMemberCottonResponse> giveCotton(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "cottonType",
			description = "솜뭉치 타입",
			in = ParameterIn.PATH,
			example = "DAILY") @PathVariable CottonType cottonType
	);

	@Operation(summary = "홈 화면 불러오기", description = "홈 화면을 불러온다.")
	@ApiSuccessResponse
	SuccessResponse<GetHomeInfoResponse> getMemberHomeInfo(
		@Parameter(hidden = true)
		Principal principal
	);

	@Operation(summary = "회원 정보 조회", description = "회원의 정보를 조회합니다.")
	@ApiSuccessResponse
	SuccessResponse<MemberProfileResponse> getMemberProfile(
		@Parameter(hidden = true)
		Principal principal
	);

	@Operation(summary = "출석체크", description = "회원의 최근 방문일자를 기록한다.")
	@ApiSuccessResponse
	SuccessResponse<?> visit(
		@Parameter(hidden = true)
		Principal principal
	);
}
