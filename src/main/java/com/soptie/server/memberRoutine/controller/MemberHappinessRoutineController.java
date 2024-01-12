package com.soptie.server.memberRoutine.controller;

import com.soptie.server.common.dto.Response;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.service.MemberHappinessRoutineService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

import static com.soptie.server.common.dto.Response.success;
import static com.soptie.server.memberRoutine.message.ResponseMessage.SUCCESS_CREATE_ROUTINE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/happiness/member")
public class MemberHappinessRoutineController {

    private final MemberHappinessRoutineService memberHappinessRoutineService;

    @PostMapping
    public ResponseEntity<Response> createMemberDailyRoutine(
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
}
