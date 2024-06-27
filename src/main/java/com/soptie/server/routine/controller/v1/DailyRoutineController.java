package com.soptie.server.routine.controller.v1;

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
import com.soptie.server.routine.controller.v1.dto.response.DailyRoutineListAcquireResponse;
import com.soptie.server.routine.message.RoutineSuccessMessage;
import com.soptie.server.routine.service.RoutineService;
import com.soptie.server.theme.service.ThemeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily")
public class DailyRoutineController implements DailyRoutineControllerDocs {

	private final RoutineService routineService;
	private final ThemeService themeService;

	@GetMapping
	public ResponseEntity<SuccessResponse<DailyRoutineListAcquireResponse>> acquireAllByThemes(
		@RequestParam List<Long> themes
	) {
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_ROUTINE.getMessage(),
			DailyRoutineListAcquireResponse.from(routineService.acquireAllInDailyByThemeIds(themes))));
	}

	@GetMapping("/theme/{themeId}")
	public ResponseEntity<SuccessResponse<DailyRoutineListAcquireResponse>> acquireAllByTheme(
		Principal principal,
		@PathVariable long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_ROUTINE.getMessage(),
			DailyRoutineListAcquireResponse.from(
				themeService.acquireById(themeId),
				routineService.acquireAllInDailyNotInMemberByThemeId(memberId, themeId))));
	}
}
