package com.soptie.server.domain.usecase;

import com.soptie.server.api.controller.dto.response.doll.DollImageResponse;
import com.soptie.server.domain.doll.DollType;

public interface DollService {
	DollImageResponse getDollImage(DollType type);
}
