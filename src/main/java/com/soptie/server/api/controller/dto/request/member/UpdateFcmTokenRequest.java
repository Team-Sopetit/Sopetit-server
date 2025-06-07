package com.soptie.server.api.controller.dto.request.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UpdateFcmTokenRequest(
	@Schema(description = "알림 발송을 위한 Fcm Token", example = "token")
	@NotNull String fcmToken
) {
}
