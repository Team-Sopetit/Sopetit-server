package com.soptie.server.api.controller.auth.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestBody;

import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.auth.dto.SignInRequest;
import com.soptie.server.api.controller.auth.dto.SignInResponse;
import com.soptie.server.api.controller.auth.dto.TokenGetResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.AUTH_NAME, description = ApiTagConstants.AUTH_DESCRIPTION)
public interface AuthApiDocs {

	@Operation(summary = "소셜 로그인", description = "소셜 로그인을 진행한다.")
	@ApiSuccessResponse
	SuccessResponse<SignInResponse> signIn(
		@Parameter(hidden = true)
		String socialAccessToken,

		@RequestBody
		SignInRequest request
	);

	@Operation(summary = "토큰 재발급", description = "리프레시 토큰을 이용해 액세스 토큰을 재발급 받는다.")
	@ApiSuccessResponse
	SuccessResponse<TokenGetResponse> reissueToken(
		@Parameter(hidden = true)
		String refreshToken
	);

	@Operation(summary = "로그아웃", description = "로그아웃을 한다.")
	@ApiSuccessResponse
	SuccessResponse<?> signOut(
		@Parameter(hidden = true)
		Principal principal
	);

	@Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 진행한다.")
	SuccessResponse<?> withdrawal(
		@Parameter(hidden = true)
		Principal principal
	);
}
