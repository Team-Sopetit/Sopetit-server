package com.soptie.server.temporary.dto;

import com.soptie.server.domain.doll.DollType;

import lombok.NonNull;

public record DollResponse(
	@NonNull String faceImageUrl
) {

	public static DollResponse of(DollType type) {
		return new DollResponse(getFaceImageUrl(type));
	}

	private static String getFaceImageUrl(DollType type) {
		return switch (type) {
			case BROWN -> "https://github.com/Team-Sopetit/sopetit-image/blob/main/character/brown/face.png?raw=true";
			case RED -> "https://github.com/Team-Sopetit/sopetit-image/blob/main/character/red/face.png?raw=true";
			case GRAY -> "https://github.com/Team-Sopetit/sopetit-image/blob/main/character/gray/face.png?raw=true";
			case WHITE -> "https://github.com/Team-Sopetit/sopetit-image/blob/main/character/white/face.png?raw=true";
		};
	}
}
