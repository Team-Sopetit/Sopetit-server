package com.soptie.server.api.controller.achievement;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.achievement.docs.AchievementApiV3Docs;
import com.soptie.server.api.controller.achievement.dto.AchievedThemeResponse;
import com.soptie.server.api.controller.achievement.dto.AchievedThemesResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.domain.achievement.AchievedThemeService;
import com.soptie.server.domain.achievement.Achievement;
import com.soptie.server.domain.theme.Theme;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/achievement")
public class AchievementApiV3 implements AchievementApiV3Docs {
	private final AchievedThemeService achievedThemeService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/themes")
	public SuccessResponse<AchievedThemesResponse> getAchievementByThemes(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		Map<Theme, Integer> response = achievedThemeService.getAchievedThemes(memberId);
		return SuccessResponse.from(AchievedThemesResponse.from(response));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/themes/{themeId}/routines")
	public SuccessResponse<AchievedThemeResponse> getAchievementTheme(
		Principal principal,
		@PathVariable long themeId
	) {
		val memberId = Long.parseLong(principal.getName());
		Achievement response = achievedThemeService.getAchievementTheme(memberId, themeId);
		return SuccessResponse.from(AchievedThemeResponse.from(response));
	}
}
