package com.soptie.server.memberRoutine.controller;

import com.soptie.server.common.dto.Response;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.service.MemberHappinessRoutineService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

import static com.soptie.server.common.dto.Response.success;
import static com.soptie.server.memberRoutine.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness/member")
public class MemberHappinessRoutineController {

    private final MemberHappinessRoutineService memberHappinessRoutineService;

    @PostMapping
    public ResponseEntity<Response> createMemberHappinessRoutine(
            Principal principal, @RequestBody MemberHappinessRoutineRequest request) {
        val memberId = Long.parseLong(principal.getName());
        val response = memberHappinessRoutineService.createMemberHappinessRoutine(memberId, request);
        return ResponseEntity
                .created(getURI())
                .body(success(SUCCESS_CREATE_ROUTINE.getMessage(), response));
    }

    private URI getURI() {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand()
                .toUri();
    }

    @GetMapping
    public ResponseEntity<Response> getMemberHappinessRoutine(@NonNull Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        val response = memberHappinessRoutineService.getMemberHappinessRoutine(memberId);
		return response
                .map(result -> ResponseEntity.ok(success(SUCCESS_GET_ROUTINE.getMessage(), result)))
				.orElseGet(() -> ResponseEntity.noContent().build());
	}

    @DeleteMapping("/routine/{routineId}")
    public ResponseEntity<Response> deleteMemberHappinessRoutine(Principal principal, @PathVariable Long routineId) {
        val memberId = Long.parseLong(principal.getName());
        memberHappinessRoutineService.deleteMemberHappinessRoutine(memberId, routineId);
        return ResponseEntity.ok(success(SUCCESS_DELETE_ROUTINE.getMessage()));
    }

    @PatchMapping("/routine/{routineId}")
    public ResponseEntity<Response> achieveMemberHappinessRoutine(Principal principal, @PathVariable Long routineId){
        val memberId = Long.parseLong(principal.getName());
        memberHappinessRoutineService.achieveMemberHappinessRoutine(memberId, routineId);
        return ResponseEntity.ok(success(SUCCESS_ACHIEVE_ROUTINE.getMessage()));
    }
}
