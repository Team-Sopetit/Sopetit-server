package com.soptie.server.doll.dto;

import com.soptie.server.doll.entity.Doll;

public record DollImageResponse(
	String faceImageUrl
) {

	public static DollImageResponse of(Doll doll) {
		return new DollImageResponse(doll.getImageInfo().getFaceImageUrl());
	}
}
