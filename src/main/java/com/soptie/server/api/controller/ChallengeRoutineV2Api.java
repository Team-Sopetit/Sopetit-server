package com.soptie.server.api.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.ChallengeRoutineV2ApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.routine.ChallengeRoutineListAcquireResponseV2;
import com.soptie.server.domain.routine.RoutineService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/challenge")
public class ChallengeRoutineV2Api implements ChallengeRoutineV2ApiDocs {

	private final RoutineService routineService;

	@GetMapping
	public ResponseEntity<SuccessResponse<ChallengeRoutineListAcquireResponseV2>> acquireAllByTheme(
		Principal principal,
		@RequestParam long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = routineService.acquireAllInChallengeWithThemeId(memberId, themeId);
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_CHALLENGE_ROUTINE.getMessage(),
			ChallengeRoutineListAcquireResponseV2.from(response)));
	}
}
