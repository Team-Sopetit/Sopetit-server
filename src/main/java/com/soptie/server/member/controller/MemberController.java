package com.soptie.server.member.controller;

import com.soptie.server.common.dto.Response;
import com.soptie.server.member.dto.CottonCountResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.CottonType;
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
import static com.soptie.server.member.message.ResponseMessage.SUCCESS_GIVE_COTTON;
import static com.soptie.server.member.message.ResponseMessage.*;

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

    private URI getURI() {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand()
                .toUri();
    }

    @PatchMapping("/{cottonType}")
    public ResponseEntity<Response> giveCotton(Principal principal, @PathVariable CottonType cottonType) {
        val memberId = Long.parseLong(principal.getName());
        val cottonCount = memberService.giveCotton(memberId, cottonType);
        val cottonCountResponse = CottonCountResponse.of(cottonCount);
        return ResponseEntity.ok(success(SUCCESS_GIVE_COTTON.getMessage(), cottonCountResponse));
    }

    @GetMapping
    public ResponseEntity<Response> getMemberHomeInfo(Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        val memberHomeInfoResponse = memberService.getMemberHomeInfo(memberId);
        return ResponseEntity.ok(success(SUCCESS_HOME_INFO.getMessage(), memberHomeInfoResponse));

    }
}
