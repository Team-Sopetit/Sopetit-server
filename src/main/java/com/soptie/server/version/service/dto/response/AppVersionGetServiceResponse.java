package com.soptie.server.version.service.dto.response;

import static lombok.AccessLevel.*;

import lombok.Builder;

@Builder(access = PRIVATE)
public record AppVersionGetServiceResponse(
		VersionServiceResponse iosVersion,
		VersionServiceResponse androidVersion,
		String notificationTitle,
		String notificationContent
) {

	public static AppVersionGetServiceResponse of(
			String iOSAppVersion,
			String iosForceUpdateVersion,
			String androidAppVersion,
			String androidForceUpdateVersion,
			String notificationTitle,
			String notificationContent
	) {
		return AppVersionGetServiceResponse.builder()
				.iosVersion(VersionServiceResponse.of(iOSAppVersion, iosForceUpdateVersion))
				.androidVersion(VersionServiceResponse.of(androidAppVersion, androidForceUpdateVersion))
				.notificationTitle(notificationTitle)
				.notificationContent(notificationContent)
				.build();
	}

	@Builder(access = PRIVATE)
	public record VersionServiceResponse(
			String appVersion,
			String forceUpdateVersion
	) {

		private static VersionServiceResponse of(String appVersion, String forceUpdateVersion) {
			return VersionServiceResponse.builder()
					.appVersion(appVersion)
					.forceUpdateVersion(forceUpdateVersion)
					.build();
		}
	}
}
