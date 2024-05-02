package com.soptie.server.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.val;

@Configuration
@SecurityScheme(
		name = "Authorization",
		in = SecuritySchemeIn.HEADER,
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "Bearer",
		description = "Authorization: Bearer ~"
)
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		val info = new Info()
				.title("SOFTIE API Document")
				.description("소프티 API 명세서")
				.version("1.0.0");

		return new OpenAPI()
				.components(new Components())
				.info(info);
	}
}
