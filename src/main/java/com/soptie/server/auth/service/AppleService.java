package com.soptie.server.auth.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AppleService {

    String getAppleData(String socialAccessToken) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
