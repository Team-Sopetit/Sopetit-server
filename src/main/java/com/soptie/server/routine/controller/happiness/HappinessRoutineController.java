package com.soptie.server.routine.controller.happiness;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessSubRoutinesResponse;
import com.soptie.server.routine.service.HappinessRoutineService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.routine.message.RoutineSuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness")
public class HappinessRoutineController implements HappinessRoutineApi {

    private final HappinessRoutineService happinessRoutineService;

    @GetMapping
    public ResponseEntity<SuccessResponse<HappinessRoutinesResponse>> getHappinessRoutinesByThemes(@RequestParam(required = false) Long themeId) {
        val response = happinessRoutineService.getHappinessRoutinesByTheme(themeId);
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_ROUTINE.getMessage(), response));
    }

    @GetMapping("/routine/{routineId}")
    public ResponseEntity<SuccessResponse<HappinessSubRoutinesResponse>> getHappinessSubRoutinesByRoutineOfTheme(@PathVariable long routineId) {
        val response = happinessRoutineService.getHappinessSubRoutines(routineId);
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_SUB_ROUTINES.getMessage(), response));
    }
}
