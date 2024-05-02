package com.soptie.server.theme.controller.v1;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.theme.message.ThemeSuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.theme.controller.v1.api.HappinessThemeApiV1;
import com.soptie.server.theme.controller.v1.dto.response.HappinessThemeListGetResponse;
import com.soptie.server.theme.service.ThemeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness/themes")
public class HappinessThemeControllerV1 implements HappinessThemeApiV1 {

    private final ThemeService themeService;

    @GetMapping
    public ResponseEntity<SuccessResponse<HappinessThemeListGetResponse>> getHappinessThemes() {
        val response = HappinessThemeListGetResponse.of(themeService.getThemes());
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_THEME.getMessage(), response));
    }
}
