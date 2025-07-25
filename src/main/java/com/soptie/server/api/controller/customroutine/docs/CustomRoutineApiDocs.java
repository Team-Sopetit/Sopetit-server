package com.soptie.server.api.controller.customroutine.docs;

import java.security.Principal;

import com.soptie.server.api.common.annotation.ApiCreatedResponse;
import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.customroutine.dto.CustomRoutineRequest;
import com.soptie.server.api.controller.customroutine.dto.CustomRoutineResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.CUSTOM_ROUTINE_NAME, description = ApiTagConstants.CUSTOM_ROUTINE_DESCRIPTION)
public interface CustomRoutineApiDocs {

	@Operation(summary = "커스텀 루틴 생성", description = "커스텀 루틴을 생성한다.")
	@ApiCreatedResponse
	SuccessResponse<CustomRoutineResponse> create(
		@Parameter(hidden = true)
		Principal principal,

		@RequestBody
		CustomRoutineRequest requestDto
	);

	@Operation(summary = "커스텀 루틴 수정", description = "커스텀 루틴을 수정한다.")
	@ApiSuccessResponse
	SuccessResponse<CustomRoutineResponse> update(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "customRoutineId",
			description = "수정하려는 커스텀루틴(회원루틴) id",
			in = ParameterIn.PATH,
			required = true) long customRoutineId,

		@RequestBody
		CustomRoutineRequest requestDto
	);

	@Operation(summary = "커스텀 루틴 삭제", description = "커스텀 루틴을 삭제한다.")
	@ApiSuccessResponse
	SuccessResponse<?> delete(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "customRoutineId",
			description = "삭제하려는 커스텀루틴(회원루틴) id",
			in = ParameterIn.PATH,
			required = true) long customRoutineId
	);
}
