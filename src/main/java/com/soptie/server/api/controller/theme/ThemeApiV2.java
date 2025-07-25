package com.soptie.server.api.controller.theme;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.theme.docs.ThemeApiV2Docs;
import com.soptie.server.api.controller.theme.dto.GetThemesResponse;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.domain.theme.ThemeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/themes")
public class ThemeApiV2 implements ThemeApiV2Docs {

	private final ThemeService themeService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public SuccessResponse<GetThemesResponse> getAll() {
		List<Theme> themes = themeService.getAll();
		return SuccessResponse.from(GetThemesResponse.from(themes));
	}
}
