package com.soptie.server.version.dto;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record AppVersionResponse(
		VersionResponse iosVersion,
		VersionResponse androidVersion,
		String notificationContent
) {

	public static AppVersionResponse of(String iOSAppVersion, String iosForceUpdateVersion,
			String androidAppVersion, String androidForceUpdateVersion, String notificationContent) {
		return AppVersionResponse.builder()
				.iosVersion(new VersionResponse(iOSAppVersion, iosForceUpdateVersion))
				.androidVersion(new VersionResponse(androidAppVersion, androidForceUpdateVersion))
				.notificationContent(notificationContent)
				.build();
	}

	private record VersionResponse(
			String appVersion,
			String forceUpdateVersion
	) {
	}
}
