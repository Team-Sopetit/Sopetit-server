package com.soptie.server.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class TimeUtils {

	public static LocalDate toDateTime(LocalDateTime localDateTime) {
		return Optional.ofNullable(localDateTime)
			.map(LocalDateTime::toLocalDate)
			.orElse(null);
	}
}
