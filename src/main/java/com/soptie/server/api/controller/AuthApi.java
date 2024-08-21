package com.soptie.server.api.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.AuthApiDocs;
import com.soptie.server.api.controller.dto.request.auth.SignInRequest;
import com.soptie.server.api.controller.dto.response.BaseResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.auth.SignInResponse;
import com.soptie.server.api.controller.dto.response.auth.TokenGetResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.auth.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApi implements AuthApiDocs {

	private final AuthService authService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse<SignInResponse> signIn(
		@RequestHeader("Authorization") String socialAccessToken,
		@RequestBody SignInRequest request
	) {
		val response = authService.signIn(socialAccessToken, request);
		return SuccessResponse.success(SuccessMessage.SUCCESS_SIGN_IN.getMessage(), response);
	}

	@PostMapping("/token")
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse<TokenGetResponse> reissueToken(
		@RequestHeader("Authorization") String refreshToken
	) {
		val response = authService.reissueToken(refreshToken);
		return SuccessResponse.success(SuccessMessage.SUCCESS_RECREATE_TOKEN.getMessage(), response);
	}

	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.OK)
	public BaseResponse signOut(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		authService.signOut(memberId);
		return SuccessResponse.success(SuccessMessage.SUCCESS_SIGN_OUT.getMessage());
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public BaseResponse withdrawal(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		authService.withdraw(memberId);
		return SuccessResponse.success(SuccessMessage.SUCCESS_WITHDRAWAL.getMessage());
	}
}
