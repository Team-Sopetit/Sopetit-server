package com.soptie.server.maker.controller.dto.response;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.maker.service.dto.MakerListAcquireServiceResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MakerListAcquireResponse(
	List<MakerAcquireResponse> makers
) {

	public static MakerListAcquireResponse from(MakerListAcquireServiceResponse response) {
		return MakerListAcquireResponse.builder()
			.makers(response.makers().stream().map(MakerAcquireResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record MakerAcquireResponse(
		long makerId,
		@NonNull String content,
		@NonNull String name,
		@NonNull String profileImageUrl,
		@NonNull List<String> tags,
		long themeId,
		@NonNull String description,
		@NonNull String themeName,
		@NonNull String modifier
	) {

		public static MakerAcquireResponse from(MakerListAcquireServiceResponse.MakerAcquireServiceResponse maker) {
			return MakerAcquireResponse.builder()
				.makerId(maker.makerId())
				.content(maker.content())
				.name(maker.name())
				.profileImageUrl(maker.profileImageUrl())
				.tags(maker.tags())
				.themeId(maker.themeId())
				.description(maker.description())
				.themeName(maker.themeName())
				.modifier(maker.modifier())
				.build();
		}
	}
}
