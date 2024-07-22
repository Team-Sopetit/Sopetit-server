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
		long themeId,
		@NonNull String profileImageUrl,
		@NonNull String description,
		@NonNull String content,
		@NonNull List<String> tags
	) {

		public static MakerAcquireResponse from(MakerListAcquireServiceResponse.MakerAcquireServiceResponse maker) {
			return MakerAcquireResponse.builder()
				.makerId(maker.makerId())
				.themeId(maker.themeId())
				.profileImageUrl(maker.profileImageUrl())
				.description(maker.description())
				.content(maker.content())
				.tags(maker.tags())
				.build();
		}
	}
}
