package com.soptie.server.routine.controller.v1;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.routine.message.RoutineSuccessMessage.*;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v1.docs.DailyRoutineControllerDocs;
import com.soptie.server.routine.controller.v1.dto.response.DailyRoutineListByThemeGetResponse;
import com.soptie.server.routine.controller.v1.dto.response.DailyRoutineListByThemesGetResponse;
import com.soptie.server.routine.service.RoutineService;
import com.soptie.server.routine.service.dto.request.DailyRoutineListByThemeGetServiceRequest;
import com.soptie.server.routine.service.dto.request.DailyRoutineListByThemesGetServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily")
public class DailyRoutineController implements DailyRoutineControllerDocs {

	private final RoutineService routineService;

	@GetMapping
	public ResponseEntity<SuccessResponse<DailyRoutineListByThemesGetResponse>> getRoutinesByThemes(
		@RequestParam List<Long> themes
	) {
		val response = DailyRoutineListByThemesGetResponse
			.of(routineService.getRoutinesByThemes(DailyRoutineListByThemesGetServiceRequest.of(themes)));
		return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
	}

	@GetMapping("/theme/{themeId}")
	public ResponseEntity<SuccessResponse<DailyRoutineListByThemeGetResponse>> getRoutinesByTheme(
		Principal principal,
		@PathVariable long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = DailyRoutineListByThemeGetResponse
			.of(routineService.getRoutinesByTheme(DailyRoutineListByThemeGetServiceRequest.of(memberId, themeId)));
		return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
	}
}
