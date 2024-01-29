package com.soptie.server.version.service;

import com.soptie.server.version.dto.AppVersionResponse;

public interface VersionService {
	AppVersionResponse getClientAppVersion();
}
