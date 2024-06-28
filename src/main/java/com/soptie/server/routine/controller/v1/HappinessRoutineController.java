package com.soptie.server.routine.controller.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v1.docs.HappinessRoutineControllerDocs;
import com.soptie.server.routine.controller.v1.dto.response.HappinessRoutineListAcquireResponse;
import com.soptie.server.routine.controller.v1.dto.response.HappinessSubRoutineListAcquireResponse;
import com.soptie.server.routine.message.RoutineSuccessMessage;
import com.soptie.server.routine.service.RoutineService;
import com.soptie.server.routine.service.dto.request.HappinessSubRoutineListGetServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness")
public class HappinessRoutineController implements HappinessRoutineControllerDocs {

	private final RoutineService routineService;

	@GetMapping
	public ResponseEntity<SuccessResponse<HappinessRoutineListAcquireResponse>> getHappinessRoutinesByThemes(
		@RequestParam(required = false) Long themeId
	) {
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_HAPPINESS_ROUTINE.getMessage(),
			HappinessRoutineListAcquireResponse.from(routineService.acquireAllInHappinessByThemeId(themeId))));
	}

	@GetMapping("/routine/{routineId}")
	public ResponseEntity<SuccessResponse<HappinessSubRoutineListAcquireResponse>> getHappinessSubRoutinesByRoutineOfTheme(
		@PathVariable long routineId
	) {
		val response = HappinessSubRoutineListAcquireResponse.of(
			routineService.getHappinessSubRoutines(HappinessSubRoutineListGetServiceRequest.of(routineId)));
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_HAPPINESS_SUB_ROUTINES.getMessage(),
			response));
	}
}
