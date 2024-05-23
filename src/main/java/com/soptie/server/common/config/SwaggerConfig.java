package com.soptie.server.common.config;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.*;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.val;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		val securityScheme = new SecurityScheme();
		securityScheme.setType(HTTP);
		securityScheme.setScheme("bearer");
		securityScheme.setBearerFormat("JWT");

		val components = new Components();
		components.addSecuritySchemes("BearerAuthentication", securityScheme);

		val securityRequirement = new SecurityRequirement();
		securityRequirement.addList("BearerAuthentication");

		val info = new Info();
		info.setTitle("SOFTIE API Document");
		info.setDescription("소프티 API 명세서");
		info.setVersion("1.0.0");

		return new OpenAPI()
				.components(components)
				.security(List.of(securityRequirement))
				.info(info);
	}
}
