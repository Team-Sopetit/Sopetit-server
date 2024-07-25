package com.soptie.server.maker.service.dto;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.maker.entity.Maker;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MakerListAcquireServiceResponse(
	List<MakerAcquireServiceResponse> makers
) {

	public static MakerListAcquireServiceResponse from(List<Maker> makers) {
		return MakerListAcquireServiceResponse.builder()
			.makers(makers.stream().map(MakerAcquireServiceResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record MakerAcquireServiceResponse(
		long makerId,
		long themeId,
		@NonNull String profileImageUrl,
		@NonNull String description,
		@NonNull String content,
		@NonNull List<String> tags
	) {

		public static MakerAcquireServiceResponse from(Maker maker) {
			return MakerAcquireServiceResponse.builder()
				.makerId(maker.getId())
				.themeId(maker.getThemeId())
				.profileImageUrl(maker.getProfileImageUrl())
				.description(maker.getDescription())
				.content(maker.getContent())
				.tags(maker.getTags())
				.build();
		}
	}
}
