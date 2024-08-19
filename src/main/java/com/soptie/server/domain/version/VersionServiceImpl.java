package com.soptie.server.domain.version;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.common.support.ValueConfig;
import com.soptie.server.domain.usecase.VersionService;

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
