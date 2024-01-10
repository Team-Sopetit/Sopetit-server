package com.soptie.server.auth.controller;

import com.soptie.server.auth.dto.SignInRequest;
import com.soptie.server.auth.message.ResponseMessage;
import com.soptie.server.auth.service.AuthService;
import com.soptie.server.common.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.soptie.server.common.dto.Response.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Response> signIn(@RequestHeader("Authorization") String socialAccessToken, @RequestBody SignInRequest request) {
        val response = authService.signIn(socialAccessToken, request);
        return ResponseEntity.ok(success(ResponseMessage.SUCCESS_SIGNIN.getMessage(), response));
    }
}
