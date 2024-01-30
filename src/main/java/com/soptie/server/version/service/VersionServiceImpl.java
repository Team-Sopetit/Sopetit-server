package com.soptie.server.version.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.common.config.ValueConfig;
import com.soptie.server.version.dto.AppVersionResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VersionServiceImpl implements VersionService {

	private final ValueConfig valueConfig;

	@Override
	public AppVersionResponse getClientAppVersion() {
		return AppVersionResponse.of(
				valueConfig.getIOS_APP_VERSION(),
				valueConfig.getIOS_FORCE_UPDATE_VERSION(),
				valueConfig.getANDROID_APP_VERSION(),
				valueConfig.getANDROID_FORCE_UPDATE_VERSION(),
				valueConfig.getNOTIFICATION_TITLE(),
				valueConfig.getNOTIFICATION_CONTENT());
	}
}
