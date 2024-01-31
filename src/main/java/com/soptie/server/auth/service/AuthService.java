package com.soptie.server.auth.service;

import com.soptie.server.auth.dto.SignInRequest;
import com.soptie.server.auth.dto.SignInResponse;
import com.soptie.server.auth.dto.TokenResponse;

public interface AuthService {

    SignInResponse signIn(String socialAccessToken, SignInRequest request);
    void signOut(long memberId);
    void withdraw(long memberId);
    TokenResponse reissueToken(String refreshToken);
}
