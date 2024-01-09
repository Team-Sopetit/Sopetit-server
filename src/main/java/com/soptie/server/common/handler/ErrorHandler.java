package com.soptie.server.common.handler;

import static com.soptie.server.common.dto.Response.*;
import static org.springframework.http.HttpStatus.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soptie.server.common.dto.Response;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Response> entityNotFoundException(EntityNotFoundException exception) {
		log.error(exception.getMessage());
		return ResponseEntity.status(NOT_FOUND).body(fail(exception.getMessage()));
	}
}
