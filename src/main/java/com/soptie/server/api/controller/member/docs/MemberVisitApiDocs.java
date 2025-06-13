package com.soptie.server.api.controller.member.docs;

import java.security.Principal;

import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.MEMBER_NAME, description = ApiTagConstants.MEMBER_DESCRIPTION)
public interface MemberVisitApiDocs {

	@Operation(summary = "출석체크", description = "회원의 최근 방문일자를 기록한다.")
	@ApiSuccessResponse
	SuccessResponse<?> visit(
		@Parameter(hidden = true)
		Principal principal
	);

}
