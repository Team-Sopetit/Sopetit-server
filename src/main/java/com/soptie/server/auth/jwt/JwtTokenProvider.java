package com.soptie.server.auth.jwt;

import com.soptie.server.common.config.ValueConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

import static com.soptie.server.auth.jwt.JwtValidationType.*;
import static io.jsonwebtoken.Header.*;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.util.Base64.getEncoder;

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
            return VALID_JWT;
        } catch (MalformedJwtException exception) {
            log.error(exception.getMessage());
            return INVALID_JWT_TOKEN;
        } catch (ExpiredJwtException exception) {
            log.error(exception.getMessage());
            return EXPIRED_JWT_TOKEN;
        } catch (UnsupportedJwtException exception) {
            log.error(exception.getMessage());
            return UNSUPPORTED_JWT_TOKEN;
        } catch (IllegalArgumentException exception) {
            log.error(exception.getMessage());
            return EMPTY_JWT;
        }
    }

    private Claims generateClaims(Authentication authentication) {
        val claims = Jwts.claims();
        claims.put("memberId", authentication.getPrincipal());
        return claims;
    }

    public Long getUserFromJwt(String token) {
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
