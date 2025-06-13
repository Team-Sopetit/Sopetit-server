package com.soptie.server.api.controller.version.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetAppVersionResponse(
	@Schema(description = "iOS 버전 정보")
	VersionResponse iosVersion,
	@Schema(description = "Android 버전 정보")
	VersionResponse androidVersion,
	@Schema(description = "업데이트 안내 제목", example = "업데이트 알림")
	String notificationTitle,
	@Schema(description = "업데이트 안내 내용", example = "업데이트하세요.")
	String notificationContent
) {

	public static GetAppVersionResponse of(
		String iosAppVersion,
		String iosAppForceVersion,
		String androidAppVersion,
		String androidAppForceVersion,
		String notificationTitle,
		String notificationContent
	) {
		return GetAppVersionResponse.builder()
			.iosVersion(VersionResponse.of(iosAppVersion, iosAppForceVersion))
			.androidVersion(VersionResponse.of(androidAppVersion, androidAppForceVersion))
			.notificationTitle(notificationTitle)
			.notificationContent(notificationContent)
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record VersionResponse(
		String appVersion,
		String forceUpdateVersion
	) {

		private static VersionResponse of(String appVersion, String forceUpdateVersion) {
			return VersionResponse.builder()
				.appVersion(appVersion)
				.forceUpdateVersion(forceUpdateVersion)
				.build();
		}
	}
}
