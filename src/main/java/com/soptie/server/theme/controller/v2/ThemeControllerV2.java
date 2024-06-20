package com.soptie.server.theme.controller.v2;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.theme.message.ThemeSuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.theme.controller.v2.dto.response.ThemeListAcquireResponse;
import com.soptie.server.theme.service.ThemeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/themes")
public class ThemeControllerV2 implements ThemeApi {

	private final ThemeService themeService;

	@GetMapping
	public ResponseEntity<SuccessResponse<ThemeListAcquireResponse>> acquireAllInBasic() {
		val response = ThemeListAcquireResponse.from(themeService.acquireAllInBasic());
		return ResponseEntity.ok(success(SUCCESS_ACQUIRE_ALL.getMessage(), response));
	}
}
