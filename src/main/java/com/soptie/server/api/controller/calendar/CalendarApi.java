package com.soptie.server.api.controller.calendar;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.common.constants.MessageConstants;
import com.soptie.server.api.controller.calendar.docs.CalendarApiDocs;
import com.soptie.server.api.controller.calendar.dto.DateHistoryResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.domain.calendar.CalendarService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/calendar")
public class CalendarApi implements CalendarApiDocs {

	private final CalendarService calendarService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public SuccessResponse<Map<LocalDate, DateHistoryResponse>> getCalendar(
		final Principal principal,
		@RequestParam final int year,
		@RequestParam final int month
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = calendarService.getCalendar(memberId, year, month);
		return SuccessResponse.success(MessageConstants.GET_CALENDAR.getMessage(), response);
	}
}
