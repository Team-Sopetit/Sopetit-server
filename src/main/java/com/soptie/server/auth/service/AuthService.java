package com.soptie.server.auth.service;

import com.soptie.server.auth.dto.SignInRequest;
import com.soptie.server.auth.dto.SignInResponse;

public interface AuthService {

    SignInResponse signIn(String socialAccessToken, SignInRequest request);
}