package com.soptie.server.routine.controller;

import static com.soptie.server.common.dto.Response.*;
import static com.soptie.server.routine.message.ResponseMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.Response;
import com.soptie.server.routine.service.DailyRoutineServiceImpl;

import lombok.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily")
public class DailyRoutineController {

	private final DailyRoutineServiceImpl dailyRoutineService;

	@GetMapping("/themes")
	public ResponseEntity<Response> getThemes() {
		val response = dailyRoutineService.getThemes();
		return ResponseEntity.ok(success(SUCCESS_GET_THEME.getMessage(), response));
	}

	@GetMapping
	public ResponseEntity<Response> getRoutines(@RequestParam String themes) {
		val response = dailyRoutineService.getRoutinesByThemes(themes);
		return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
	}
}
