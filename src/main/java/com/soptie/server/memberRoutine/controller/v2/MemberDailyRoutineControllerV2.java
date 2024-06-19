package com.soptie.server.memberRoutine.controller.v2;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.memberRoutine.controller.v2.api.MemberDailyRoutineApi;
import com.soptie.server.memberRoutine.controller.v2.dto.response.MemberDailyRoutineListGetResponseV2;
import com.soptie.server.memberRoutine.service.MemberRoutineReadService;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineListGetServiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.soptie.server.common.dto.SuccessResponse.success;
import static com.soptie.server.memberRoutine.message.SuccessMessage.SUCCESS_GET_ROUTINE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/routines/daily/member")
public class MemberDailyRoutineControllerV2 implements MemberDailyRoutineApi {

    private final MemberRoutineReadService memberRoutineReadService;

    @GetMapping
    public ResponseEntity<SuccessResponse<MemberDailyRoutineListGetResponseV2>> acquireAll(
            Principal principal
    ) {
        val memberId = Long.parseLong(principal.getName());
        val response = MemberDailyRoutineListGetResponseV2
                .of(memberRoutineReadService.acquireAll(MemberDailyRoutineListGetServiceRequest.of(memberId)));
        return ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), response));
    }
}
