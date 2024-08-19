package com.soptie.server.api.web.jwt;

import static io.jsonwebtoken.lang.Strings.*;
import static org.springframework.http.HttpHeaders.*;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.soptie.server.common.support.ValueConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		try {
			val token = getAccessTokenFromRequest(request);
			if (hasText(token) && jwtTokenProvider.validateToken(token) == JwtValidationType.VALID_JWT) {
				val authentication = new UserAuthentication(getMemberId(token), null, null);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception exception) {
			log.error(exception.getMessage());
		}

		filterChain.doFilter(request, response);
	}

	private long getMemberId(String token) {
		return jwtTokenProvider.getUserFromJwt(token);
	}

	private String getAccessTokenFromRequest(HttpServletRequest request) {
		return isContainsAccessToken(request) ? getAuthorizationAccessToken(request) : null;
	}

	private boolean isContainsAccessToken(HttpServletRequest request) {
		val authorization = request.getHeader(AUTHORIZATION);
		return authorization != null && authorization.startsWith(ValueConfig.BEARER_HEADER);
	}

	private String getAuthorizationAccessToken(HttpServletRequest request) {
		return getTokenFromBearerString(request.getHeader(AUTHORIZATION));
	}

	private String getTokenFromBearerString(String token) {
		return token.replaceFirst(ValueConfig.BEARER_HEADER, ValueConfig.BLANK);
	}
}
