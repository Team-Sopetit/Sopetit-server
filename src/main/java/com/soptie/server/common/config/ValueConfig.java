package com.soptie.server.common.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
@Getter
public class ValueConfig {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri")
    private String tokenUri;

    @Value("${jwt.KAKAO_URL}")
    private String kakaoUri;

    @Value("${jwt.APPLE_URL}")
    private String appleUri;

    @Value("${jwt.ACCESS_TOKEN_EXPIRED}")
    private Long accessTokenExpired;

    @Value("${jwt.REFRESH_TOKEN_EXPIRED}")
    private Long refreshTokenExpired;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
