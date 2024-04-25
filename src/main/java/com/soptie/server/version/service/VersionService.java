package com.soptie.server.version.service;

import com.soptie.server.version.service.dto.response.AppVersionGetServiceResponse;

public interface VersionService {
	AppVersionGetServiceResponse getClientAppVersion();
}
