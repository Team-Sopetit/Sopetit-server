package com.soptie.server.routine.controller;

import static com.soptie.server.common.dto.Response.*;
import static com.soptie.server.routine.message.ResponseMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.Response;
import com.soptie.server.routine.service.DailyRoutineService;

import lombok.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily")
public class DailyRoutineController {

	private final DailyRoutineService dailyRoutineService;

	@GetMapping("/themes")
	public ResponseEntity<Response> getThemes() {
		val response = dailyRoutineService.getThemes();
		return ResponseEntity.ok(success(SUCCESS_GET_THEME.getMessage(), response));
	}

	@GetMapping
	public ResponseEntity<Response> getRoutinesByThemes(@RequestParam String themes) {
		val response = dailyRoutineService.getRoutinesByThemes(themes);
		return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
	}

	@GetMapping("/{themeId}")
	public ResponseEntity<Response> getRoutinesByTheme(@PathVariable Long themeId) {
		val response = dailyRoutineService.getRoutinesByTheme(themeId);
		return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
	}
}
