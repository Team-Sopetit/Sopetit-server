package com.soptie.server.routine.controller.happiness;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.happiness.dto.HappinessThemeListGetResponse;
import com.soptie.server.routine.service.happiness.HappinessRoutineService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.routine.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness")
public class HappinessRoutineController implements HappinessRoutineApi {

    private final HappinessRoutineService happinessRoutineService;

    @GetMapping("/themes")
    public ResponseEntity<SuccessResponse<HappinessThemeListGetResponse>> getHappinessThemes() {
        val response = happinessRoutineService.getHappinessThemes();
        return ResponseEntity.ok(of(SUCCESS_GET_HAPPINESS_THEME.getMessage(), response));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<HappinessThemeListGetResponse>> getHappinessRoutinesByThemes(@RequestParam(required = false) Long themeId) {
        val response = happinessRoutineService.getHappinessRoutinesByTheme(themeId);
        return ResponseEntity.ok(of(SUCCESS_GET_HAPPINESS_ROUTINE.getMessage(), response));
    }

    @GetMapping("/routine/{routineId}")
    public ResponseEntity<SuccessResponse<HappinessThemeListGetResponse>> getHappinessSubRoutinesByRoutineOfTheme(@PathVariable long routineId) {
        val response = happinessRoutineService.getHappinessSubRoutines(routineId);
        return ResponseEntity.ok(of(SUCCESS_GET_HAPPINESS_SUB_ROUTINES.getMessage(), response));
    }
}
