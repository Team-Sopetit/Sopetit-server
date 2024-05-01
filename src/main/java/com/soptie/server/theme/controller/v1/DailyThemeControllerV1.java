package com.soptie.server.theme.controller.v1;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.theme.message.ThemeSuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.theme.controller.v1.dto.response.DailyThemeListGetResponse;
import com.soptie.server.theme.service.ThemeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily/themes")
public class DailyThemeControllerV1 implements DailyThemeApiV1 {

	private final ThemeService themeService;

	@GetMapping
	public ResponseEntity<SuccessResponse<DailyThemeListGetResponse>> getThemes() {
		val response = DailyThemeListGetResponse.of(themeService.getThemes());
		return ResponseEntity.ok(success(SUCCESS_GET_THEME.getMessage(), response));
	}
}
