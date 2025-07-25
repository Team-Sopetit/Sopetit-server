package com.soptie.server.api.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soptie.server.api.controller.generic.dto.ErrorResponse;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.helper.webhook.WebhookLogger;
import com.soptie.server.common.helper.webhook.model.WebhookLoggerRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorHandler {

	private final WebhookLogger webhookLogger;

	@ExceptionHandler(SoftieException.class)
	public ResponseEntity<ErrorResponse> softieException(SoftieException exception) {
		log.error(exception.getMessage());
		return ResponseEntity
			.status(exception.getStatusCode())
			.body(ErrorResponse.of("[" + exception.getDefaultMessage() + "] " + exception.getDetailMessage()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> exception(RuntimeException exception) {
		log.error(exception.getMessage());
		webhookLogger.send(WebhookLoggerRequest.error(exception));
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
			.body(ErrorResponse.of(exception.getMessage()));
	}
}
