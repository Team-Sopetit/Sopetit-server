package com.soptie.server.domain.usecase;

import com.soptie.server.domain.version.AppVersionGetServiceResponse;

public interface VersionService {
	AppVersionGetServiceResponse getClientAppVersion();
}
