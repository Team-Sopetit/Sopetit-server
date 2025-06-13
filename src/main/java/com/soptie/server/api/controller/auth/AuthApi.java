package com.soptie.server.api.controller.auth;

import java.security.Principal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.common.constants.MessageConstants;
import com.soptie.server.api.controller.auth.docs.AuthApiDocs;
import com.soptie.server.api.controller.auth.dto.SignInRequest;
import com.soptie.server.api.controller.auth.dto.SignInResponse;
import com.soptie.server.api.controller.auth.dto.TokenGetResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.domain.auth.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApi implements AuthApiDocs {

	private final AuthService authService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public SuccessResponse<SignInResponse> signIn(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String socialAccessToken,
		@RequestBody SignInRequest request
	) {
		val response = authService.signIn(socialAccessToken, request);
		return SuccessResponse.success(MessageConstants.SUCCESS_SIGN_IN.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/token")
	public SuccessResponse<TokenGetResponse> reissueToken(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken
	) {
		val response = authService.reissueToken(refreshToken);
		return SuccessResponse.success(MessageConstants.SUCCESS_RECREATE_TOKEN.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/logout")
	public SuccessResponse<?> signOut(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		authService.signOut(memberId);
		return SuccessResponse.success(MessageConstants.SUCCESS_SIGN_OUT.getMessage());
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping
	public SuccessResponse<?> withdrawal(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		authService.withdraw(memberId);
		return SuccessResponse.success(MessageConstants.SUCCESS_WITHDRAWAL.getMessage());
	}
}
