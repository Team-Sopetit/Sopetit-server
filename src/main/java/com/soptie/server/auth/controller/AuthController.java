package com.soptie.server.auth.controller;

import com.soptie.server.auth.controller.dto.request.SignInRequest;
import com.soptie.server.auth.controller.dto.response.SignInResponse;
import com.soptie.server.auth.controller.dto.response.TokenGetResponse;
import com.soptie.server.auth.service.dto.request.SignInServiceRequest;
import com.soptie.server.auth.service.dto.request.TokenGetServiceRequest;
import com.soptie.server.auth.service.dto.response.TokenGetServiceResponse;
import com.soptie.server.auth.service.AuthService;
import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.SuccessResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.soptie.server.auth.message.SuccessMessage.*;
import static com.soptie.server.common.dto.SuccessResponse.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthApi {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<SuccessResponse<SignInResponse>> signIn(
            @RequestHeader("Authorization") String socialAccessToken,
            @RequestBody SignInRequest request
    ) {
        val response = SignInResponse.of(authService.signIn(SignInServiceRequest.of(socialAccessToken, request)));
        return ResponseEntity.ok(of(SUCCESS_SIGN_IN.getMessage(), response));
    }

    @PostMapping("/token")
    public ResponseEntity<SuccessResponse<TokenGetResponse>> reissueToken(
            @RequestHeader("Authorization") String refreshToken
    ) {
        val response = TokenGetResponse.of(authService.reissueToken(TokenGetServiceRequest.of(refreshToken)));
        return ResponseEntity.ok(of(SUCCESS_RECREATE_TOKEN.getMessage(), response));
    }

    @PostMapping("/logout")
    public ResponseEntity<BaseResponse> signOut(Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        authService.signOut(memberId);
        return ResponseEntity.ok(of(SUCCESS_SIGN_OUT.getMessage()));
    }

    @DeleteMapping
    public ResponseEntity<BaseResponse> withdrawal(Principal principal) {
        val memberId = Long.parseLong(principal.getName());
        authService.withdraw(memberId);
        return ResponseEntity.ok(of(SUCCESS_WITHDRAWAL.getMessage()));
    }
}
