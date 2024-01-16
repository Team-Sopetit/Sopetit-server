package com.soptie.server.common.handler;

import static com.soptie.server.common.dto.Response.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soptie.server.auth.exception.AuthException;
import com.soptie.server.common.dto.Response;
import com.soptie.server.doll.exception.DollException;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.routine.exception.RoutineException;

import lombok.extern.slf4j.Slf4j;
import lombok.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<Response> authException(AuthException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(fail(errorCode.getMessage()));
	}

	@ExceptionHandler(DollException.class)
	public ResponseEntity<Response> dollException(DollException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(fail(errorCode.getMessage()));
	}

	@ExceptionHandler(MemberException.class)
	public ResponseEntity<Response> memberException(MemberException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(fail(errorCode.getMessage()));
	}

	@ExceptionHandler(RoutineException.class)
	public ResponseEntity<Response> routineException(RoutineException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(fail(errorCode.getMessage()));
	}
}
