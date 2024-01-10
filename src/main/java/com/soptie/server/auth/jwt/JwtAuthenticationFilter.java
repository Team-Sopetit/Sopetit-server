package com.soptie.server.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.soptie.server.auth.jwt.JwtValidationType.VALID_JWT;
import static io.jsonwebtoken.lang.Strings.hasText;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_HEADER = "Bearer ";
    private static final int BEARER_HEADER_LENGTH = 7;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getAccessTokenFromRequest(request);
            if (hasText(token) && jwtTokenProvider.validateToken(token) == VALID_JWT) {
                Long userId = jwtTokenProvider.getUserFromJwt(token);
                UserAuthentication authentication = new UserAuthentication(userId, null, null); // 아이디, 비밀번호, 권한
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception exception) {
            log.error("error : ", exception);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessTokenFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION);
        return (hasText(authorization) && authorization.startsWith(BEARER_HEADER)) ? authorization.substring(BEARER_HEADER_LENGTH) : null;
    }
}
