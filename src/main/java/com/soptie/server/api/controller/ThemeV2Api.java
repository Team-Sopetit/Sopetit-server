package com.soptie.server.api.controller;

import static com.soptie.server.api.controller.dto.response.SuccessResponse.*;
import static com.soptie.server.common.message.ThemeSuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.ThemeV2ApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.theme.ThemeListAcquireResponse;
import com.soptie.server.domain.theme.ThemeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/themes")
public class ThemeV2Api implements ThemeV2ApiDocs {

	private final ThemeService themeService;

	@GetMapping
	public ResponseEntity<SuccessResponse<ThemeListAcquireResponse>> acquireAllInBasic() {
		val response = ThemeListAcquireResponse.from(themeService.acquireAllInBasic());
		return ResponseEntity.ok(success(SUCCESS_ACQUIRE_ALL.getMessage(), response));
	}
}
