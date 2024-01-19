package com.soptie.server.doll.service;

import com.soptie.server.doll.dto.DollImageResponse;
import com.soptie.server.doll.entity.DollType;

public interface DollService {
	DollImageResponse getDollImage(DollType type);
}
