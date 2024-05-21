package com.soptie.server.version.controller.dto.response;

import static lombok.AccessLevel.PRIVATE;

import com.soptie.server.version.service.dto.response.AppVersionGetServiceResponse;
import com.soptie.server.version.service.dto.response.AppVersionGetServiceResponse.VersionServiceResponse;

import lombok.Builder;

@Builder(access = PRIVATE)
public record AppVersionGetResponse(
		VersionResponse iosVersion,
		VersionResponse androidVersion,
		String notificationTitle,
		String notificationContent
) {

	public static AppVersionGetResponse of(AppVersionGetServiceResponse response) {
		return AppVersionGetResponse.builder()
				.iosVersion(VersionResponse.of(response.iosVersion()))
				.androidVersion(VersionResponse.of(response.androidVersion()))
				.notificationTitle(response.notificationTitle())
				.notificationContent(response.notificationContent())
				.build();
	}

	@Builder(access = PRIVATE)
	private record VersionResponse(
			String appVersion,
			String forceUpdateVersion
	) {

		private static VersionResponse of(VersionServiceResponse response) {
			return VersionResponse.builder()
					.appVersion(response.appVersion())
					.forceUpdateVersion(response.forceUpdateVersion())
					.build();
		}
	}
}
