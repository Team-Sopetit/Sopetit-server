package com.soptie.server.routine.controller;

import com.soptie.server.common.dto.Response;
import com.soptie.server.routine.service.HappinessRoutineService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.soptie.server.common.dto.Response.success;
import static com.soptie.server.routine.message.ResponseMessage.SUCCESS_GET_HAPPINESS_ROUTINE;
import static com.soptie.server.routine.message.ResponseMessage.SUCCESS_GET_HAPPINESS_THEME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness")

public class HappinessRoutineController {

    private final HappinessRoutineService happinessRoutineService;

    @GetMapping("/themes")
    public ResponseEntity<Response> getHappinessThemes() {
        val response = happinessRoutineService.getHappinessThemes();
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_THEME.getMessage(), response));
    }

    @GetMapping
    public ResponseEntity<Response> getHappinessRoutinesByThemes(@RequestParam(required = false) Long themeId) {
        val response = happinessRoutineService.getHappinessRoutinesByTheme(themeId);
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_ROUTINE.getMessage(), response));
    }

}
