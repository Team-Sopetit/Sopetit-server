package com.soptie.server.memberRoutine.controller.v2;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.memberRoutine.message.SuccessMessage.*;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.memberRoutine.controller.v2.dto.response.MemberChallengeRoutineAchieveResponse;
import com.soptie.server.memberRoutine.service.MemberRoutineUpdateService;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutineAchieveServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/challenge/member")
public class MemberChallengeRoutineController implements MemberChallengeRoutineApi {

    private final MemberRoutineUpdateService memberRoutineUpdateService;

    @PatchMapping("/routine/{routineId}")
    public ResponseEntity<BaseResponse> achieve(
            Principal principal,
            @PathVariable Long routineId
    ) {
        val memberId = Long.parseLong(principal.getName());
        val response = memberRoutineUpdateService.achieveMemberRoutine(
                MemberRoutineAchieveServiceRequest.of(memberId, routineId));
        return ResponseEntity.ok(success(
                SUCCESS_ACHIEVE_ROUTINE.getMessage(),
                MemberChallengeRoutineAchieveResponse.of(response)
        ));
    }
}
