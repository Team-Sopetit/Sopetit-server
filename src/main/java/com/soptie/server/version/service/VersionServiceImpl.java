package com.soptie.server.version.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.common.config.ValueConfig;
import com.soptie.server.version.service.dto.response.AppVersionGetServiceResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VersionServiceImpl implements VersionService {

	@Override
	public AppVersionGetServiceResponse getClientAppVersion() {
		return AppVersionGetServiceResponse.of(
			ValueConfig.IOS_APP_VERSION,
			ValueConfig.IOS_FORCE_UPDATE_VERSION,
			ValueConfig.ANDROID_APP_VERSION,
			ValueConfig.ANDROID_FORCE_UPDATE_VERSION,
			ValueConfig.NOTIFICATION_TITLE,
			ValueConfig.NOTIFICATION_CONTENT);
	}
}
