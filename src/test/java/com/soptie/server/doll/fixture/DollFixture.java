package com.soptie.server.doll.fixture;

import com.soptie.server.doll.dto.DollImageResponse;

public class DollFixture {

	private static final String FACE_IMAGE_URL = "face-image-url";

	public static DollImageResponse createDollImageResponseDTO() {
		return new DollImageResponse(FACE_IMAGE_URL);
	}
}
