package com.soptie.server.routine.controller.v1;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.routine.message.RoutineSuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v1.docs.HappinessRoutineControllerDocs;
import com.soptie.server.routine.controller.v1.dto.response.HappinessRoutineListGetResponse;
import com.soptie.server.routine.controller.v1.dto.response.HappinessSubRoutineListGetResponse;
import com.soptie.server.routine.service.RoutineService;
import com.soptie.server.routine.service.dto.request.HappinessRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.request.HappinessSubRoutineListGetServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness")
public class HappinessRoutineController implements HappinessRoutineControllerDocs {

	private final RoutineService routineService;

	@GetMapping
	public ResponseEntity<SuccessResponse<HappinessRoutineListGetResponse>> getHappinessRoutinesByThemes(
		@RequestParam(required = false) Long themeId) {
		val response = HappinessRoutineListGetResponse.of(
			routineService.getHappinessRoutinesByTheme(HappinessRoutineListGetServiceRequest.of(themeId)));
		return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_ROUTINE.getMessage(), response));
	}

	@GetMapping("/routine/{routineId}")
	public ResponseEntity<SuccessResponse<HappinessSubRoutineListGetResponse>> getHappinessSubRoutinesByRoutineOfTheme(
		@PathVariable long routineId) {
		val response = HappinessSubRoutineListGetResponse.of(
			routineService.getHappinessSubRoutines(HappinessSubRoutineListGetServiceRequest.of(routineId)));
		return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_SUB_ROUTINES.getMessage(), response));
	}
}
