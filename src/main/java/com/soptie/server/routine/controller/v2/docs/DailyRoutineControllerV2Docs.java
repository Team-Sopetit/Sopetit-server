package com.soptie.server.routine.controller.v2.docs;

import java.util.LinkedHashSet;

import org.springframework.http.ResponseEntity;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v2.dto.response.DailyRoutineListAcquireResponseV2;

import io.swagger.v3.oas.annotations.Parameter;

public interface DailyRoutineControllerV2Docs {

	ResponseEntity<SuccessResponse<DailyRoutineListAcquireResponseV2>> acquireAllByThemes(
		@Parameter(name = "list of themes id", description = "조회할 테마 id 목록") LinkedHashSet<Long> themeIds
	);
}
