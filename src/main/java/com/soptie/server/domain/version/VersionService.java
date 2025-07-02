package com.soptie.server.domain.version;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.commons.lang.Pair;
import com.soptie.server.api.controller.version.dto.GetAppVersionResponse;
import com.soptie.server.persistence.global.PropertyStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VersionService {

	private final PropertyStore propertyStore;

	private static final String IOS_APP_VERSION = "IOS_APP_VERSION";
	private static final String IOS_FORCE_UPDATE_VERSION = "IOS_FORCE_UPDATE_VERSION";
	private static final String ANDROID_APP_VERSION = "ANDROID_APP_VERSION";
	private static final String ANDROID_FORCE_UPDATE_VERSION = "ANDROID_FORCE_UPDATE_VERSION";
	private static final String NOTIFICATION_TITLE = "NOTIFICATION_TITLE";
	private static final String NOTIFICATION_CONTENT = "NOTIFICATION_CONTENT";
	private static final String SOFTIE_SURVEY_STATUS = "SOFTIE_SURVEY_STATUS";

	public GetAppVersionResponse getClientAppVersion() {
		List<String> targetKeys = List.of(SOFTIE_SURVEY_STATUS);

		return GetAppVersionResponse.builder()
			.iosVersion(createIosVersion())
			.androidVersion(createAndroidVersion())
			.notificationTitle(propertyStore.get(NOTIFICATION_TITLE))
			.notificationContent(propertyStore.get(NOTIFICATION_CONTENT))
			.properties(createProperties(targetKeys))
			.build();
	}

	private Map<String, String> createProperties(List<String> targetKeys) {
		return targetKeys.stream()
			.map(key -> Pair.of(key, propertyStore.get(key)))
			.filter(pair -> pair.getFirst() != null && pair.getSecond() != null)
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
	}

	private GetAppVersionResponse.VersionResponse createIosVersion() {
		return GetAppVersionResponse.VersionResponse.builder()
			.appVersion(propertyStore.get(IOS_APP_VERSION))
			.forceUpdateVersion(propertyStore.get(IOS_FORCE_UPDATE_VERSION))
			.build();
	}

	private GetAppVersionResponse.VersionResponse createAndroidVersion() {
		return GetAppVersionResponse.VersionResponse.builder()
			.appVersion(propertyStore.get(ANDROID_APP_VERSION))
			.forceUpdateVersion(propertyStore.get(ANDROID_FORCE_UPDATE_VERSION))
			.build();
	}
}
