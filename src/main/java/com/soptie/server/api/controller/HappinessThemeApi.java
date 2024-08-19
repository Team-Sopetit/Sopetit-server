package com.soptie.server.api.controller;

import static com.soptie.server.api.controller.dto.response.SuccessResponse.*;
import static com.soptie.server.common.message.ThemeSuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.HappinessThemeApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.theme.HappinessThemeListGetResponse;
import com.soptie.server.domain.theme.ThemeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness/themes")
public class HappinessThemeApi implements HappinessThemeApiDocs {

	private final ThemeService themeService;

	@GetMapping
	public ResponseEntity<SuccessResponse<HappinessThemeListGetResponse>> acquireAllInBasic() {
		val response = HappinessThemeListGetResponse.from(themeService.acquireAllInBasic());
		return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_THEME.getMessage(), response));
	}
}
