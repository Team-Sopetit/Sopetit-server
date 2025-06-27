package com.soptie.server.domain.version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.version.dto.GetAppVersionResponse;
import com.soptie.server.persistence.adapter.PropertyAdapter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VersionService {

	private final PropertyAdapter propertyAdapter;

	private static final String IOS_APP_VERSION = "IOS_APP_VERSION";
	private static final String IOS_FORCE_UPDATE_VERSION = "IOS_FORCE_UPDATE_VERSION";
	private static final String ANDROID_APP_VERSION = "ANDROID_APP_VERSION";
	private static final String ANDROID_FORCE_UPDATE_VERSION = "ANDROID_FORCE_UPDATE_VERSION";
	private static final String NOTIFICATION_TITLE = "NOTIFICATION_TITLE";
	private static final String NOTIFICATION_CONTENT = "NOTIFICATION_CONTENT";
	private static final String SOFTIE_SURVEY_STATUS = "SOFTIE_SURVEY_STATUS";

	private static final List<String> versionKeys = List.of(
		IOS_APP_VERSION,
		IOS_FORCE_UPDATE_VERSION,
		ANDROID_APP_VERSION,
		ANDROID_FORCE_UPDATE_VERSION,
		NOTIFICATION_TITLE,
		NOTIFICATION_CONTENT
	);

	public GetAppVersionResponse getClientAppVersion() {
		List<String> propertiesKeys = List.of(SOFTIE_SURVEY_STATUS);

		List<String> finalKeys = Stream.concat(versionKeys.stream(), propertiesKeys.stream()).toList();
		Map<String, String> properties = propertyAdapter.getByKeys(finalKeys);

		return GetAppVersionResponse.builder()
			.iosVersion(createIosVersion(properties))
			.androidVersion(createAndroidVersion(properties))
			.notificationTitle(properties.get(NOTIFICATION_TITLE))
			.notificationContent(properties.get(NOTIFICATION_CONTENT))
			.properties(createProperties(properties, propertiesKeys))
			.build();
	}

	private Map<String, String> createProperties(Map<String, String> properties, List<String> targetKeys) {
		Map<String, String> adhocProperties = new HashMap<>();
		targetKeys.stream()
			.filter(properties::containsKey)
			.forEach(key -> adhocProperties.put(key, properties.get(key)));
		return adhocProperties;
	}

	private GetAppVersionResponse.VersionResponse createIosVersion(Map<String, String> properties) {
		return GetAppVersionResponse.VersionResponse.builder()
			.appVersion(properties.get(IOS_APP_VERSION))
			.forceUpdateVersion(properties.get(IOS_FORCE_UPDATE_VERSION))
			.build();
	}

	private GetAppVersionResponse.VersionResponse createAndroidVersion(Map<String, String> properties) {
		return GetAppVersionResponse.VersionResponse.builder()
			.appVersion(properties.get(ANDROID_APP_VERSION))
			.forceUpdateVersion(properties.get(ANDROID_FORCE_UPDATE_VERSION))
			.build();
	}
}
