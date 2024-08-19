package com.soptie.server.api.controller;

import static com.soptie.server.common.message.AuthMessage.*;
import static com.soptie.server.api.controller.dto.response.SuccessResponse.*;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.AuthApiDocs;
import com.soptie.server.domain.usecase.AuthService;
import com.soptie.server.domain.auth.SignInServiceRequest;
import com.soptie.server.domain.auth.TokenGetServiceRequest;
import com.soptie.server.api.controller.dto.response.BaseResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.request.auth.SignInRequest;
import com.soptie.server.api.controller.dto.response.auth.SignInResponse;
import com.soptie.server.api.controller.dto.response.auth.TokenGetResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApi implements AuthApiDocs {

	private final AuthService authService;

	@PostMapping
	public ResponseEntity<SuccessResponse<SignInResponse>> signIn(
		@RequestHeader("Authorization") String socialAccessToken,
		@RequestBody SignInRequest request
	) {
		val response = SignInResponse.of(authService.signIn(SignInServiceRequest.of(socialAccessToken, request)));
		return ResponseEntity.ok(success(SUCCESS_SIGN_IN.getMessage(), response));
	}

	@PostMapping("/token")
	public ResponseEntity<SuccessResponse<TokenGetResponse>> reissueToken(
		@RequestHeader("Authorization") String refreshToken
	) {
		val response = TokenGetResponse.of(authService.reissueToken(TokenGetServiceRequest.of(refreshToken)));
		return ResponseEntity.ok(success(SUCCESS_RECREATE_TOKEN.getMessage(), response));
	}

	@PostMapping("/logout")
	public ResponseEntity<BaseResponse> signOut(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		authService.signOut(memberId);
		return ResponseEntity.ok(success(SUCCESS_SIGN_OUT.getMessage()));
	}

	@DeleteMapping
	public ResponseEntity<BaseResponse> withdrawal(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		authService.withdraw(memberId);
		return ResponseEntity.ok(success(SUCCESS_WITHDRAWAL.getMessage()));
	}
}
