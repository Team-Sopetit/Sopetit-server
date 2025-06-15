package com.soptie.server.api.controller.memberroutine.docs;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.memberroutine.dto.AchieveMemberRoutineResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.MEMBER_ROUTINE_NAME, description = ApiTagConstants.MEMBER_ROUTINE_DESCRIPTION)
public interface MemberRoutineApiDocs {

	@Operation(summary = "데일리 루틴 삭제", description = "회원의 데일리 루틴을 삭제한다.")
	@ApiSuccessResponse
	SuccessResponse<?> deleteMemberRoutines(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "routines",
			description = "삭제할 회원의 데일리 루틴 id 목록",
			in = ParameterIn.QUERY,
			example = "1,2,3"
		) @RequestParam List<Long> routines
	);

	@Operation(summary = "데일리 루틴 달성", description = "회원의 데일리 루틴을 달성한다.")
	@ApiSuccessResponse
	SuccessResponse<AchieveMemberRoutineResponse> achieveMemberRoutine(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "routineId",
			description = "달성한 회원의 데일리 루틴 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable long routineId
	);

	@Operation(summary = "루틴 기록 삭제", description = "달성한 루틴 기록을 삭제합니다.")
	@ApiSuccessResponse
	SuccessResponse<?> deleteRoutineHistory(
		@Parameter(
			name = "historyId",
			description = "달성 이력 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable long historyId
	);
}
