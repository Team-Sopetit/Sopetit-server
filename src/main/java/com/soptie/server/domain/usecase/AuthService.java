package com.soptie.server.domain.usecase;

import com.soptie.server.domain.auth.SignInServiceRequest;
import com.soptie.server.domain.auth.TokenGetServiceRequest;
import com.soptie.server.domain.auth.SignInServiceResponse;
import com.soptie.server.domain.auth.TokenGetServiceResponse;

public interface AuthService {

	SignInServiceResponse signIn(SignInServiceRequest request);

	void signOut(long memberId);

	void withdraw(long memberId);

	TokenGetServiceResponse reissueToken(TokenGetServiceRequest request);
}
