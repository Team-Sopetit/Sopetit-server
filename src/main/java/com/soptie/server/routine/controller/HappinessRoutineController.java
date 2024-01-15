package com.soptie.server.routine.controller;

import com.soptie.server.common.dto.Response;
import com.soptie.server.routine.dto.HappinessSubRoutinesResponse;
import com.soptie.server.routine.service.HappinessRoutineService;
import com.soptie.server.routine.service.HappinessSubRoutineService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.soptie.server.common.dto.Response.success;
import static com.soptie.server.routine.message.ResponseMessage.*;

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

    @GetMapping("/routine/{routineId}")
    public ResponseEntity<Response> getHappinessSubRoutinesByRoutineOfTheme(@PathVariable("routineId") Long routineId) {
        val response = happinessRoutineService.getHappinessSubRoutines(routineId);
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_SUB_ROUTINES.getMessage(), response));
    }
}
