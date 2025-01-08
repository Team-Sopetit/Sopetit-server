package com.soptie.server.api.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.AchievementApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.achievement.AchievedThemeResponse;
import com.soptie.server.api.controller.dto.response.achievement.AchievedThemesResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.achievement.AchievedThemeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/achievement")
public class AchievementApi implements AchievementApiDocs {
	private final AchievedThemeService achievedThemeService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/themes")
	public SuccessResponse<AchievedThemesResponse> getAchievementThemes(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val response = achievedThemeService.getAchievedThemes(memberId);
		return SuccessResponse.success(SuccessMessage.GET_STATISTICS.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/theme/{themeId}/routines")
	public SuccessResponse<AchievedThemeResponse> getAchievementTheme(
		Principal principal,
		@PathVariable long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = achievedThemeService.getAchievementTheme(memberId, themeId);
		return SuccessResponse.success(SuccessMessage.GET_STATISTICS.getMessage(), response);
	}
}
