package com.soptie.server.api.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.docs.DailyRoutineApiDocs;
import com.soptie.server.api.controller.dto.response.routine.DailyRoutineListAcquireResponse;
import com.soptie.server.common.message.RoutineSuccessMessage;
import com.soptie.server.domain.routine.RoutineService;
import com.soptie.server.domain.theme.ThemeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily")
public class DailyRoutineApi implements DailyRoutineApiDocs {

	private final RoutineService routineService;
	private final ThemeService themeService;

	@GetMapping
	public ResponseEntity<SuccessResponse<DailyRoutineListAcquireResponse>> acquireAllByThemes(
		@RequestParam List<Long> themes
	) {
		val response = routineService.acquireAllInDailyByThemeIds(themes);
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_ROUTINE.getMessage(),
			DailyRoutineListAcquireResponse.from(response)));
	}

	@GetMapping("/theme/{themeId}")
	public ResponseEntity<SuccessResponse<DailyRoutineListAcquireResponse>> acquireAllByTheme(
		Principal principal,
		@PathVariable long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		val themeResponse = themeService.acquireById(themeId);
		val routinesResponse = routineService.acquireAllInDailyNotInMemberByThemeId(memberId, themeId);
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_ROUTINE.getMessage(),
			DailyRoutineListAcquireResponse.from(themeResponse, routinesResponse)));
	}
}
