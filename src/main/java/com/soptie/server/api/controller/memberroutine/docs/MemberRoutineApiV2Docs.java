package com.soptie.server.api.controller.memberroutine.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestBody;

import com.soptie.server.api.common.annotation.ApiCreatedResponse;
import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesRequest;
import com.soptie.server.api.controller.memberroutine.dto.CreateMemberRoutinesResponse;
import com.soptie.server.api.controller.memberroutine.dto.GetMemberRoutinesResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.MEMBER_ROUTINE_NAME, description = ApiTagConstants.MEMBER_ROUTINE_DESCRIPTION)
public interface MemberRoutineApiV2Docs {

	@Operation(summary = "데일리 루틴 다중 추가", description = "회원의 데일리 루틴을 다중으로 추가한다.")
	@ApiCreatedResponse
	SuccessResponse<CreateMemberRoutinesResponse> createMemberRoutines(
		@Parameter(hidden = true)
		Principal principal,

		@RequestBody
		CreateMemberRoutinesRequest request
	);

	@Operation(summary = "회원 루틴 조회", description = "회원의 데일리 루틴 목록을 조회한다.")
	@ApiSuccessResponse
	SuccessResponse<GetMemberRoutinesResponse> getMemberRoutines(
		@Parameter(hidden = true)
		Principal principal
	);
}
