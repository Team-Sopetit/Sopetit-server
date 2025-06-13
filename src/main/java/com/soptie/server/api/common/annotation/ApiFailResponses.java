package com.soptie.server.api.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.soptie.server.api.controller.generic.dto.ErrorResponse;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses({
	@ApiResponse(
		responseCode = "4xx",
		description = "Client Error",
		content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
	@ApiResponse(
		responseCode = "500",
		description = "Server Internal Error",
		content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
})
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiFailResponses {
}
