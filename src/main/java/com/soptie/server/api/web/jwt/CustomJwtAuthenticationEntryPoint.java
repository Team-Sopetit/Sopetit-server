package com.soptie.server.api.web.jwt;

import static jakarta.servlet.http.HttpServletResponse.*;
import static org.springframework.http.MediaType.*;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soptie.server.api.controller.generic.dto.ErrorResponse;
import com.soptie.server.common.exception.ExceptionCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(
		HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException exception
	) throws IOException {
		setResponse(response);
	}

	private void setResponse(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(SC_UNAUTHORIZED);
		response.getWriter()
			.println(objectMapper.writeValueAsString(ErrorResponse.of(ExceptionCode.UNAUTHORIZED.getMessage())));
	}
}
