package com.soptie.server.member.controller;

import com.soptie.server.auth.message.ResponseMessage;
import com.soptie.server.common.dto.Response;
import com.soptie.server.member.dto.MemberHomeScreenResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

import static com.soptie.server.common.dto.Response.success;
import static com.soptie.server.member.message.ResponseMessage.SUCCESS_CREATE_PROFILE;
import static com.soptie.server.member.message.ResponseMessage.SUCCESS_HOME_SCREEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Response> createMemberProfile(Principal principal, @RequestBody MemberProfileRequest request) {
        val memberId = Long.parseLong(principal.getName());
        memberService.createMemberProfile(memberId, request);
        return ResponseEntity.created(getURI())
                .body(success(SUCCESS_CREATE_PROFILE.getMessage(), null));
    }

    @GetMapping
    public ResponseEntity<Response> showMemberHomeScreen(Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        MemberHomeScreenResponse memberHomeScreenResponse = memberService.showMemberHomeScreen(memberId);
        return ResponseEntity.ok(success(SUCCESS_HOME_SCREEN.getMessage(), memberHomeScreenResponse));
    }

    private URI getURI() {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand()
                .toUri();
    }
}
