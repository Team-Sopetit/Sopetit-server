package com.soptie.server.api.web.jwt;

import static io.jsonwebtoken.Header.*;
import static io.jsonwebtoken.security.Keys.*;
import static java.util.Base64.*;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.soptie.server.common.support.ValueConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	private final ValueConfig valueConfig;

	public String generateToken(Authentication authentication, long expiration) {
		return Jwts.builder()
			.setHeaderParam(TYPE, JWT_TYPE)
			.setClaims(generateClaims(authentication))
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expiration))
			.signWith(getSigningKey())
			.compact();
	}

	public JwtValidationType validateToken(String token) {
		try {
			getBody(token);
			return JwtValidationType.VALID_JWT;
		} catch (MalformedJwtException exception) {
			log.error(exception.getMessage());
			return JwtValidationType.INVALID_JWT_TOKEN;
		} catch (ExpiredJwtException exception) {
			log.error(exception.getMessage());
			return JwtValidationType.EXPIRED_JWT_TOKEN;
		} catch (UnsupportedJwtException exception) {
			log.error(exception.getMessage());
			return JwtValidationType.UNSUPPORTED_JWT_TOKEN;
		} catch (IllegalArgumentException exception) {
			log.error(exception.getMessage());
			return JwtValidationType.EMPTY_JWT;
		}
	}

	private Claims generateClaims(Authentication authentication) {
		//todo. admin 분기 처리 가능? (+AdminEntity 추가) (ex. put("adminId", ...))
		val claims = Jwts.claims();
		claims.put("memberId", authentication.getPrincipal());
		return claims;
	}

	public Long getUserFromJwt(String token) {
		//todo. admin 분기 처리 가능 (+AdminEntity 추가)
		val claims = getBody(token);
		return Long.parseLong(claims.get("memberId").toString());
	}

	private Claims getBody(final String token) {
		return Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	}

	private SecretKey getSigningKey() {
		val encodedKey = getEncoder().encodeToString(valueConfig.getSecretKey().getBytes());
		return hmacShaKeyFor(encodedKey.getBytes());
	}
}
