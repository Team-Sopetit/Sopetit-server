package com.soptie.server.api.controller.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateFcmTokenRequest(
	@Schema(description = "알림 발송을 위한 Fcm Token (푸시 알람 해제 시에는 null)", example = "token")
	String fcmToken
) {
}
