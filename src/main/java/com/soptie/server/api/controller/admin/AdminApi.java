package com.soptie.server.api.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.persistence.global.PropertyStore;
import com.soptie.server.persistence.global.RequestLimiter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminApi {

	private final PropertyStore propertyStore;
	private final RequestLimiter requestLimiter;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/properties/refresh")
	public ResponseEntity<Void> refresh(HttpServletRequest request) {
		String clientId = request.getRemoteAddr();

		if (!requestLimiter.tryAcquire(clientId)) {
			return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
		}

		propertyStore.init();
		return ResponseEntity.ok().build();
	}
}
