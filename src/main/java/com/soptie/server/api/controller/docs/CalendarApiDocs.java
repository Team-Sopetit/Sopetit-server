package com.soptie.server.api.controller.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestParam;

import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.calendar.GetCalendarResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "[Calendar] 캘린더 API Version3", description = "캘린더 API Version3")
public interface CalendarApiDocs {

	@Operation(
		summary = "캘린더 조회",
		description = "루틴 달성 기록, 메모가 포함되어있는 캘린더를 조회한다.",
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
	SuccessResponse<GetCalendarResponse> getCalendar(
		@Parameter(hidden = true) Principal principal,
		@Parameter(
			name = "year",
			description = "조회할 캘린더의 년도",
			in = ParameterIn.QUERY,
			required = true,
			example = "2024"
		) @RequestParam int year,
		@Parameter(
			name = "month",
			description = "조회할 캘린더의 월",
			in = ParameterIn.QUERY,
			required = true,
			example = "12"
		) @RequestParam int month
	);
}
