package com.soptie.server.common.support;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import lombok.Getter;

@Configuration
@Getter
public class ValueConfig {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.KAKAO_URL}")
	private String kakaoUri;

	@Value("${jwt.APPLE_URL}")
	private String appleUri;

	@Value("${jwt.ACCESS_TOKEN_EXPIRED}")
	private Long accessTokenExpired;

	@Value("${jwt.REFRESH_TOKEN_EXPIRED}")
	private Long refreshTokenExpired;

	public static final String IOS_FORCE_UPDATE_VERSION = "2.1.2";
	public static final String IOS_APP_VERSION = "2.1.2";
	public static final String ANDROID_FORCE_UPDATE_VERSION = "1.0.1";
	public static final String ANDROID_APP_VERSION = "1.0.1";
	public static final String NOTIFICATION_TITLE = "새로운 버전이 업데이트 되었어요!";
	public static final String NOTIFICATION_CONTENT = "안정적인 서비스 사용을 위해\n최신버전으로 업데이트 해주세요.";
	public static final String TOKEN_VALUE_DELIMITER = "\\.";
	public static final String BEARER_HEADER = "Bearer ";
	public static final String BLANK = "";
	public static final String MODULUS = "n";
	public static final String EXPONENT = "e";
	public static final String KID_HEADER_KEY = "kid";
	public static final String ALG_HEADER_KEY = "alg";
	public static final String RSA = "RSA";
	public static final String KEY = "keys";
	public static final String ID = "sub";
	public static final int QUOTES = 1;
	public static final int POSITIVE_NUMBER = 1;
	public static final int MIN_COTTON_COUNT = 0;
	public static final int DAILY_ROUTINE_MAX_COUNT = 3;
	public static final String MEMBER_DOLL_CONDITION = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]{1,10}$";
	public static final long MEMBER_HAS_NOT_CHALLENGE = 0;
	public static final long MEMBER_HAS_NOT_MEMO = 0;
	public static final int MEMO_MAX_LENGTH = 100;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
	}
}
