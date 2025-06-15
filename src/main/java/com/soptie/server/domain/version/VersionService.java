package com.soptie.server.domain.version;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.version.dto.GetAppVersionResponse;
import com.soptie.server.common.support.ValueConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VersionService {

	public GetAppVersionResponse getClientAppVersion() {
		return GetAppVersionResponse.of(
			ValueConfig.IOS_APP_VERSION,
			ValueConfig.IOS_FORCE_UPDATE_VERSION,
			ValueConfig.ANDROID_APP_VERSION,
			ValueConfig.ANDROID_FORCE_UPDATE_VERSION,
			ValueConfig.NOTIFICATION_TITLE,
			ValueConfig.NOTIFICATION_CONTENT);
	}
}
