package com.soptie.server.common.handler;

import static com.soptie.server.common.dto.ErrorResponse.*;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.memberDoll.exception.MemberDollException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soptie.server.auth.exception.AuthException;
import com.soptie.server.doll.exception.DollException;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.theme.exception.ThemeException;

import lombok.extern.slf4j.Slf4j;
import lombok.*;

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
}
