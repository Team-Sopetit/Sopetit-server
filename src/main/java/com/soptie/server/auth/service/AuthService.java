package com.soptie.server.auth.service;

import com.soptie.server.auth.dto.SignInRequest;
import com.soptie.server.auth.dto.SignInResponse;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthService {

    SignInResponse signIn(String socialAccessToken, SignInRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException;
    void signOut(long memberId);
    void withdraw(long memberId);
}
