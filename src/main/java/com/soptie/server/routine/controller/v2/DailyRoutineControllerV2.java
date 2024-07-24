package com.soptie.server.routine.controller.v2;

import java.security.Principal;
import java.util.LinkedHashSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v2.docs.DailyRoutineControllerV2Docs;
import com.soptie.server.routine.controller.v2.dto.response.DailyRoutineListAcquireResponseV2;
import com.soptie.server.routine.controller.v2.dto.response.DailyRoutinesAcquireResponseV2;
import com.soptie.server.routine.message.RoutineSuccessMessage;
import com.soptie.server.routine.service.RoutineService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/daily")
public class DailyRoutineControllerV2 implements DailyRoutineControllerV2Docs {

	private final RoutineService routineService;

	@GetMapping
	public ResponseEntity<SuccessResponse<DailyRoutineListAcquireResponseV2>> acquireAllByThemes(
		@RequestParam LinkedHashSet<Long> themeIds
	) {
		val response = routineService.acquireAllInDailyWithThemeId(themeIds);
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_ROUTINE.getMessage(),
			DailyRoutineListAcquireResponseV2.from(response)));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/theme/{themeId}")
	public SuccessResponse<DailyRoutinesAcquireResponseV2> acquireAllByThemeAndMember(
		Principal principal,
		@PathVariable long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = routineService.acquireAllInDailyByThemeAndMember(memberId, themeId);
		return SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_ROUTINE.getMessage(),
			DailyRoutinesAcquireResponseV2.from(response));
	}
}
