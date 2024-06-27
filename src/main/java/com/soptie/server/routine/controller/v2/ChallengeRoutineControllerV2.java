package com.soptie.server.routine.controller.v2;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v2.docs.ChallengeRoutineControllerV2Docs;
import com.soptie.server.routine.controller.v2.dto.response.ChallengeRoutineListAcquireResponseV2;
import com.soptie.server.routine.message.RoutineSuccessMessage;
import com.soptie.server.routine.service.RoutineService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/challenge")
public class ChallengeRoutineControllerV2 implements ChallengeRoutineControllerV2Docs {

	private final RoutineService routineService;

	@GetMapping
	public ResponseEntity<SuccessResponse<ChallengeRoutineListAcquireResponseV2>> acquireAllByTheme(
		Principal principal,
		@RequestParam(value = "themeId") Long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = routineService.acquireAllInChallengeWithThemeId(memberId, themeId);
		return ResponseEntity.ok(SuccessResponse.success(
			RoutineSuccessMessage.SUCCESS_GET_CHALLENGE_ROUTINE.getMessage(),
			ChallengeRoutineListAcquireResponseV2.from(response)));
	}
}
