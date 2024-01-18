package com.soptie.server.auth.controller;

import com.soptie.server.auth.dto.SignInRequest;
import com.soptie.server.auth.dto.TokenResponse;
import com.soptie.server.auth.service.AuthService;
import com.soptie.server.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

import static com.soptie.server.auth.message.SuccessMessage.*;
import static com.soptie.server.common.dto.Response.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Response> signIn(@RequestHeader("Authorization") String socialAccessToken, @RequestBody SignInRequest request) {
        val response = authService.signIn(socialAccessToken, request);
        return ResponseEntity.ok(success(SUCCESS_SIGN_IN.getMessage(), response));
    }

    @PostMapping("/token")
    public ResponseEntity<Response> reissueToken(@RequestHeader("Authorization") String refreshToken) {
        val response = authService.reissueToken(refreshToken);
        return ResponseEntity.ok(success(SUCCESS_RECREATE_TOKEN.getMessage(), response));
    }

    @PostMapping("/logout")
    public ResponseEntity<Response> signOut(Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        authService.signOut(memberId);
        return ResponseEntity.ok(success(SUCCESS_SIGN_OUT.getMessage()));
    }

    @DeleteMapping
    public ResponseEntity<Response> withdrawal(Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        authService.withdraw(memberId);
        return ResponseEntity.ok(success(SUCCESS_WITHDRAWAL.getMessage()));
    }
}
