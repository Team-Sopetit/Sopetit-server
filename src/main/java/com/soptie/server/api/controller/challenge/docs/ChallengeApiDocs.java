package com.soptie.server.api.controller.challenge.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.challenge.dto.ChallengesResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.CHALLENGE_NAME, description = ApiTagConstants.CHALLENGE_DESCRIPTION)
public interface ChallengeApiDocs {

	@Operation(summary = "테마별 챌린지 목록 조회", description = "테마별 챌린지 목록을 조회한다.")
	@ApiSuccessResponse
	SuccessResponse<ChallengesResponse> getChallengesByTheme(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "themeId",
			description = "조회하려는 테마 id",
			in = ParameterIn.QUERY,
			example = "1") @RequestParam long themeId
	);
}
