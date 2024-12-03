package com.soptie.server.api.controller.dto.response.calendar;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.util.Map;

import lombok.Builder;

@Builder(access = PRIVATE)
public record GetCalendarResponse(
	Map<LocalDate, DateHistoryResponse> response
) {

	public static GetCalendarResponse of(final Map<LocalDate, DateHistoryResponse> response) {
		return GetCalendarResponse.builder()
			.response(response)
			.build();
	}
}
