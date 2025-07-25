package com.soptie.server.api.controller.memberchallenge.docs;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.soptie.server.api.common.annotation.ApiCreatedResponse;
import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.memberchallenge.dto.CreateMemberChallengeRequest;
import com.soptie.server.api.controller.memberchallenge.dto.CreateMemberChallengeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.MEMBER_CHALLENGE_NAME, description = ApiTagConstants.MEMBER_CHALLENGE_DESCRIPTION)
public interface MemberChallengeApiDocs {

	@Operation(
		summary = "회원 챌린지 조회",
		description = "회원의 챌린지를 조회한다.",
		responses = @ApiResponse(responseCode = "204", description = "NO_CONTENT"))
	@ApiSuccessResponse
	ResponseEntity<?> getMemberChallenge(
		@Parameter(hidden = true)
		Principal principal
	);

	@Operation(summary = "회원 챌린지 추가", description = "회원의 챌린지를 추가한다.")
	@ApiCreatedResponse
	SuccessResponse<CreateMemberChallengeResponse> createMemberChallenge(
		@Parameter(hidden = true)
		Principal principal,

		@RequestBody
		CreateMemberChallengeRequest request
	);

	@Operation(summary = "회원 챌린지 삭제", description = "회원의 챌린지를 삭제한다.")
	@ApiSuccessResponse
	SuccessResponse<?> deleteMemberChallenge(
		@Parameter(hidden = true)
		Principal principal
	);

	@Operation(summary = "회원 챌린지 달성", description = "회원의 챌린지를 달성한다.")
	@ApiSuccessResponse
	SuccessResponse<?> achieveMemberChallenge(
		@Parameter(hidden = true)
		Principal principal
	);

	@Operation(summary = "챌린지 기록 삭제", description = "달성한 챌린지 기록을 삭제합니다.")
	@ApiSuccessResponse
	SuccessResponse<?> deleteHistory(
		@Parameter(
			name = "historyId",
			description = "삭제하려는 이력 id",
			in = ParameterIn.PATH,
			example = "1") @PathVariable long historyId
	);
}
