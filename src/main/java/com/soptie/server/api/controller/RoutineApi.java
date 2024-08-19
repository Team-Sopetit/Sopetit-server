package com.soptie.server.api.controller;

import java.security.Principal;
import java.util.LinkedHashSet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.RoutineApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.routine.GetRoutinesByMemberResponse;
import com.soptie.server.api.controller.dto.response.routine.GetRoutinesByThemeResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.routine.RoutineService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/daily")
public class RoutineApi implements RoutineApiDocs {
	private final RoutineService routineService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public SuccessResponse<GetRoutinesByThemeResponse> getRoutinesByThemeIds(
		@RequestParam LinkedHashSet<Long> themeIds
	) {
		val response = routineService.getRoutinesByThemeIds(themeIds);
		return SuccessResponse.success(SuccessMessage.GET_ROUTINE.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/theme/{themeId}")
	public SuccessResponse<GetRoutinesByMemberResponse> getRoutinesByThemeId(
		Principal principal,
		@PathVariable long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = routineService.getRoutinesByThemeId(memberId, themeId);
		return SuccessResponse.success(SuccessMessage.GET_ROUTINE.getMessage(), response);
	}
}
