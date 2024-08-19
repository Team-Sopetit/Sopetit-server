package com.soptie.server.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.ThemeApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.theme.GetThemesResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.theme.ThemeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/themes")
public class ThemeApi implements ThemeApiDocs {
	private final ThemeService themeService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public SuccessResponse<GetThemesResponse> getBasicThemes() {
		val response = themeService.getBasicThemes();
		return SuccessResponse.success(SuccessMessage.GET_THEME.getMessage(), response);
	}
}
