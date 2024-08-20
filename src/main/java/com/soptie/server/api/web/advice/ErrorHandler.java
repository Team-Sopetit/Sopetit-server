package com.soptie.server.api.web.advice;

import static com.soptie.server.api.controller.dto.response.ErrorResponse.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soptie.server.api.controller.dto.response.BaseResponse;
import com.soptie.server.common.exception.AuthException;
import com.soptie.server.common.exception.DollException;
import com.soptie.server.common.exception.MakerException;
import com.soptie.server.common.exception.MemberDollException;
import com.soptie.server.common.exception.MemberException;
import com.soptie.server.common.exception.RoutineException;
import com.soptie.server.common.exception.ThemeException;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(AuthException.class)
	public ResponseEntity<BaseResponse> authException(AuthException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(of(errorCode.getMessage()));
	}

	@ExceptionHandler(DollException.class)
	public ResponseEntity<BaseResponse> dollException(DollException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(of(errorCode.getMessage()));
	}

	@ExceptionHandler(MemberException.class)
	public ResponseEntity<BaseResponse> memberException(MemberException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(of(errorCode.getMessage()));
	}

	@ExceptionHandler(MemberDollException.class)
	public ResponseEntity<BaseResponse> memberDollException(MemberDollException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(of(errorCode.getMessage()));
	}

	@ExceptionHandler(RoutineException.class)
	public ResponseEntity<BaseResponse> routineException(RoutineException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(of(errorCode.getMessage()));
	}

	@ExceptionHandler(ThemeException.class)
	public ResponseEntity<BaseResponse> routineException(ThemeException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(of(errorCode.getMessage()));
	}

	@ExceptionHandler(MakerException.class)
	public ResponseEntity<BaseResponse> makerException(MakerException exception) {
		log.error(exception.getMessage());

		val errorCode = exception.getErrorCode();
		return ResponseEntity.status(errorCode.getHttpStatus()).body(of(errorCode.getMessage()));
	}
}