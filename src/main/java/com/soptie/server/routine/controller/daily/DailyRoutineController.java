package com.soptie.server.routine.controller.daily;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.routine.message.SuccessMessage.*;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.dto.DailyRoutinesByThemeResponse;
import com.soptie.server.routine.dto.DailyRoutinesByThemesResponse;
import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.service.DailyRoutineService;

import lombok.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily")
public class DailyRoutineController implements DailyRoutineApi {

	private final DailyRoutineService dailyRoutineService;

	@GetMapping("/themes")
	public ResponseEntity<SuccessResponse<DailyThemesResponse>> getThemes() {
		val response = dailyRoutineService.getThemes();
		return ResponseEntity.ok(of(SUCCESS_GET_THEME.getMessage(), response));
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<DailyRoutinesByThemesResponse>> getRoutinesByThemes(
			@RequestParam List<Long> themes
	) {
		val response = dailyRoutineService.getRoutinesByThemes(themes);
		return ResponseEntity.ok(of(SUCCESS_GET_ROUTINE.getMessage(), response));
	}

	@GetMapping("/theme/{themeId}")
	public ResponseEntity<SuccessResponse<DailyRoutinesByThemeResponse>> getRoutinesByTheme(
			Principal principal,
			@PathVariable long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = dailyRoutineService.getRoutinesByTheme(memberId, themeId);
		return ResponseEntity.ok(of(SUCCESS_GET_ROUTINE.getMessage(), response));
	}
}
