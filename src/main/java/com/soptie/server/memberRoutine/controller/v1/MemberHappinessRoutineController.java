package com.soptie.server.memberRoutine.controller.v1;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.common.support.UriGenerator;
import com.soptie.server.memberRoutine.controller.v1.api.MemberHappinessRoutineApi;
import com.soptie.server.memberRoutine.controller.v1.dto.request.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.controller.v1.dto.response.MemberHappinessRoutineCreateResponse;
import com.soptie.server.memberRoutine.controller.v1.dto.response.MemberHappinessRoutineGetResponse;
import com.soptie.server.memberRoutine.service.MemberRoutineService;
import com.soptie.server.memberRoutine.service.dto.request.MemberHappinessRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberHappinessRoutineGetServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutineDeleteServiceRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.memberRoutine.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness/member")
public class MemberHappinessRoutineController implements MemberHappinessRoutineApi {

    private final MemberRoutineService memberRoutineService;

    @PostMapping
    public ResponseEntity<SuccessResponse<MemberHappinessRoutineCreateResponse>> createMemberHappinessRoutine(
            Principal principal, @RequestBody MemberHappinessRoutineRequest request) {
        val memberId = Long.parseLong(principal.getName());
        val response = MemberHappinessRoutineCreateResponse.of(memberRoutineService.createHappinessRoutine(
                MemberHappinessRoutineCreateServiceRequest.of(memberId, request)));
        return ResponseEntity
                .created(UriGenerator.getURI("/api/v1/routines/happiness/member", response.routineId()))
                .body(success(SUCCESS_CREATE_ROUTINE.getMessage(), response));
    }

    @GetMapping
    public ResponseEntity<?> getMemberHappinessRoutine(Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        return memberRoutineService.getHappinessRoutine(MemberHappinessRoutineGetServiceRequest.of(memberId))
                .map(response -> ResponseEntity.ok(SuccessResponse.success(
                        SUCCESS_GET_ROUTINE.getMessage(),
                        MemberHappinessRoutineGetResponse.of(response))))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @DeleteMapping("/routine/{routineId}")
    public ResponseEntity<BaseResponse> deleteMemberHappinessRoutine(Principal principal, @PathVariable Long routineId) {
        val memberId = Long.parseLong(principal.getName());
        memberRoutineService.deleteMemberRoutine(MemberRoutineDeleteServiceRequest.of(memberId, routineId));
        return ResponseEntity.ok(success(SUCCESS_DELETE_ROUTINE.getMessage()));
    }

    @PatchMapping("/routine/{routineId}")
    public ResponseEntity<BaseResponse> achieveMemberHappinessRoutine(Principal principal, @PathVariable Long routineId) {
        val memberId = Long.parseLong(principal.getName());
        memberRoutineService.achieveMemberRoutine(MemberRoutineAchieveServiceRequest.of(memberId, routineId));
        return ResponseEntity.ok(success(SUCCESS_ACHIEVE_ROUTINE.getMessage()));
    }
}
