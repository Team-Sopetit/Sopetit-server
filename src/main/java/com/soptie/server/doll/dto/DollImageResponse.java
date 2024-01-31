package com.soptie.server.doll.dto;

import com.soptie.server.doll.entity.Doll;

import lombok.NonNull;

public record DollImageResponse(
	@NonNull String faceImageUrl
) {

	public static DollImageResponse of(Doll doll) {
		return new DollImageResponse(doll.getImageInfo().getFaceImageUrl());
	}
}
