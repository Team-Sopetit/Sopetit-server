package com.soptie.server.api.controller.dto.response.doll;

import lombok.NonNull;

public record DollImageResponse(
	@NonNull String faceImageUrl
) {

	public static DollImageResponse of(Doll doll) {
		return new DollImageResponse(doll.getImageLinks().getFaceImageUrl());
	}
}
