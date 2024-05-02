package com.soptie.server.routine.controller.v1;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.v1.dto.response.HappinessRoutineListGetResponse;
import com.soptie.server.routine.controller.v1.dto.response.HappinessSubRoutineListGetResponse;
import com.soptie.server.routine.controller.v1.api.HappinessRoutineApiV1;
import com.soptie.server.routine.service.RoutineService;
import com.soptie.server.routine.service.dto.request.HappinessRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.request.HappinessSubRoutineListGetServiceRequest;

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

    private final RoutineService routineService;

    @GetMapping
    public ResponseEntity<SuccessResponse<HappinessRoutineListGetResponse>> getHappinessRoutinesByThemes(
            @RequestParam(required = false) Long themeId
    ) {
        val response = HappinessRoutineListGetResponse.of(
                routineService.getHappinessRoutinesByTheme(HappinessRoutineListGetServiceRequest.of(themeId)));
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_ROUTINE.getMessage(), response));
    }

    @GetMapping("/routine/{routineId}")
    public ResponseEntity<SuccessResponse<HappinessSubRoutineListGetResponse>> getHappinessSubRoutinesByRoutineOfTheme(
            @PathVariable long routineId
    ) {
        val response = HappinessSubRoutineListGetResponse.of(
                routineService.getHappinessSubRoutines(HappinessSubRoutineListGetServiceRequest.of(routineId)));
        return ResponseEntity.ok(success(SUCCESS_GET_HAPPINESS_SUB_ROUTINES.getMessage(), response));
    }
}
