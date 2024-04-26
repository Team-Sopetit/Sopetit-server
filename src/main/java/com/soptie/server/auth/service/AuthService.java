package com.soptie.server.auth.service;

import com.soptie.server.auth.service.dto.request.SignInServiceRequest;
import com.soptie.server.auth.service.dto.request.TokenGetServiceRequest;
import com.soptie.server.auth.service.dto.response.SignInServiceResponse;
import com.soptie.server.auth.service.dto.response.TokenGetServiceResponse;

public interface AuthService {

    SignInServiceResponse signIn(SignInServiceRequest request);
    void signOut(long memberId);
    void withdraw(long memberId);
    TokenGetServiceResponse reissueToken(TokenGetServiceRequest refreshToken);
}
