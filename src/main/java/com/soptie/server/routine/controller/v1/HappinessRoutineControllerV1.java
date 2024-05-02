package com.soptie.server.routine.controller.v1;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v1.dto.response.HappinessRoutinesGetResponse;
import com.soptie.server.routine.controller.v1.dto.response.HappinessSubRoutinesGetResponse;
import com.soptie.server.routine.controller.v1.api.HappinessRoutineApiV1;
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
public class HappinessRoutineControllerV1 implements HappinessRoutineApiV1 {

    private final HappinessRoutineService happinessRoutineService;

    @GetMapping
    public ResponseEntity<SuccessResponse<HappinessRoutinesGetResponse>> getHappinessRoutinesByThemes(@RequestParam(required = false) Long themeId) {
        val response = HappinessRoutinesGetResponse.of(happinessRoutineService.getHappinessRoutinesByTheme(themeId));
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_ROUTINE.getMessage(), response));
    }

    @GetMapping("/routine/{routineId}")
    public ResponseEntity<SuccessResponse<HappinessSubRoutinesGetResponse>> getHappinessSubRoutinesByRoutineOfTheme(@PathVariable long routineId) {
        val response = HappinessSubRoutinesGetResponse.of(happinessRoutineService.getHappinessSubRoutines(routineId));
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_SUB_ROUTINES.getMessage(), response));
    }
}
