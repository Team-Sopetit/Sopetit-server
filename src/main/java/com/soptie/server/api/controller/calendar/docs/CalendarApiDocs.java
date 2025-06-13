package com.soptie.server.api.controller.calendar.docs;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.calendar.dto.DateHistoryResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.CALENDAR_NAME, description = ApiTagConstants.CALENDAR_DESCRIPTION)
public interface CalendarApiDocs {

	@Operation(summary = "캘린더 조회", description = "루틴 달성 기록, 메모가 포함되어있는 캘린더를 조회한다.")
	@ApiSuccessResponse
	SuccessResponse<Map<LocalDate, DateHistoryResponse>> getCalendar(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "year",
			description = "조회할 캘린더의 년도",
			in = ParameterIn.QUERY,
			required = true,
			example = "2024") @RequestParam int year,

		@Parameter(
			name = "month",
			description = "조회할 캘린더의 월",
			in = ParameterIn.QUERY,
			required = true,
			example = "12") @RequestParam int month
	);
}
