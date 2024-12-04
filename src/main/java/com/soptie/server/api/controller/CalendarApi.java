package com.soptie.server.api.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.CalendarApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.calendar.GetCalendarResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
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
	public SuccessResponse<GetCalendarResponse> getCalendar(
		final Principal principal,
		@RequestParam final int year,
		@RequestParam final int month
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = calendarService.getCalendar(memberId, year, month);
		return SuccessResponse.success(SuccessMessage.GET_CALENDAR.getMessage(), response);
	}
}
