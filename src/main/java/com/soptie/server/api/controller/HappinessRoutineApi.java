package com.soptie.server.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.docs.HappinessRoutineApiDocs;
import com.soptie.server.api.controller.dto.response.routine.HappinessRoutineListAcquireResponse;
import com.soptie.server.api.controller.dto.response.routine.HappinessSubRoutineListAcquireResponse;
import com.soptie.server.common.message.RoutineSuccessMessage;
import com.soptie.server.domain.routine.RoutineService;
import com.soptie.server.domain.routine.HappinessSubRoutineListGetServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness")
public class HappinessRoutineApi implements HappinessRoutineApiDocs {

	private final RoutineService routineService;

	@GetMapping
	public ResponseEntity<SuccessResponse<HappinessRoutineListAcquireResponse>> acquireAllByTheme(
		@RequestParam(required = false) Long themeId
	) {
		val response = routineService.acquireAllInHappinessByThemeId(themeId);
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_HAPPINESS_ROUTINE.getMessage(),
			HappinessRoutineListAcquireResponse.from(response)));
	}

	@GetMapping("/routine/{routineId}")
	public ResponseEntity<SuccessResponse<HappinessSubRoutineListAcquireResponse>> acquireAllInSubByRoutine(
		@PathVariable long routineId
	) {
		val response = routineService.getHappinessSubRoutines(HappinessSubRoutineListGetServiceRequest.of(routineId));
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_HAPPINESS_SUB_ROUTINES.getMessage(),
			HappinessSubRoutineListAcquireResponse.of(response)));
	}
}
