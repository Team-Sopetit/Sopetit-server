package com.soptie.server.common.handler;

import static com.soptie.server.common.dto.Response.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soptie.server.auth.exception.AuthException;
import com.soptie.server.common.dto.Response;
import com.soptie.server.doll.exception.DollException;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import lombok.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Response> entityNotFoundException(EntityNotFoundException exception) {
		log.error(exception.getMessage());
		return ResponseEntity.status(NOT_FOUND).body(fail(exception.getMessage()));
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Response> illegalStateException(IllegalStateException exception) {
		log.error(exception.getMessage());
		return ResponseEntity.status(BAD_REQUEST).body(fail(exception.getMessage()));
	}

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
}
