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

    private final String IOS_FORCE_UPDATE_VERSION = "0.0.9";
    private final String IOS_APP_VERSION = "1.0.0";
    private final String ANDROID_FORCE_UPDATE_VERSION = "1.0.1";
    private final String ANDROID_APP_VERSION = "1.0.1";
    private final String NOTIFICATION_TITLE = "새로운 버전이 업데이트 되었어요!";
    private final String NOTIFICATION_CONTENT = "안정적인 서비스 사용을 위해\n최신버전으로 업데이트 해주세요.";
    private final String TOKEN_VALUE_DELIMITER = "\\.";
    private final String BEARER_HEADER = "Bearer ";
    private final String BLANK = "";
    private final String MODULUS = "n";
    private final String EXPONENT = "e";
    private final String KID_HEADER_KEY = "kid";
    private final String ALG_HEADER_KEY = "alg";
    private final String RSA = "RSA";
    private final String KEY = "keys";
    private final String ID = "sub";
    private final int QUOTES = 1;
    private final int POSITIVE_NUMBER = 1;

    public static final int MIN_COTTON_COUNT = 0;
    public static final int DAILY_ROUTINE_MAX_COUNT = 3;
    public static final String MEMBER_DOLL_CONDITION = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]{1,10}$";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
