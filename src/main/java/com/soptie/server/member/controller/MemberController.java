package com.soptie.server.member.controller;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.member.dto.CottonCountResponse;
import com.soptie.server.member.dto.MemberHomeInfoResponse;
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

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.member.message.SuccessMessage.SUCCESS_CREATE_PROFILE;
import static com.soptie.server.member.message.SuccessMessage.SUCCESS_GIVE_COTTON;
import static com.soptie.server.member.message.SuccessMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
public class MemberController implements MemberApi {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<BaseResponse> createMemberProfile(Principal principal, @RequestBody MemberProfileRequest request) {
        val memberId = Long.parseLong(principal.getName());
        memberService.createMemberProfile(memberId, request);
        return ResponseEntity.created(getURI()).body(of(SUCCESS_CREATE_PROFILE.getMessage()));
    }

    private URI getURI() {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand()
                .toUri();
    }

    @PatchMapping("/cotton/{cottonType}")
    public ResponseEntity<SuccessResponse<CottonCountResponse>> giveCotton(Principal principal, @PathVariable CottonType cottonType) {
        val memberId = Long.parseLong(principal.getName());
        val response = memberService.giveCotton(memberId, cottonType);
        return ResponseEntity.ok(of(SUCCESS_GIVE_COTTON.getMessage(), response));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<MemberHomeInfoResponse>> getMemberHomeInfo(Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        val response = memberService.getMemberHomeInfo(memberId);
        return ResponseEntity.ok(of(SUCCESS_HOME_INFO.getMessage(), response));
    }
}
