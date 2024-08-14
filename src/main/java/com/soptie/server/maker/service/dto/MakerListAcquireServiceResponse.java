package com.soptie.server.maker.service.dto;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.maker.repository.dto.MakerThemeResponse;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record MakerListAcquireServiceResponse(
	List<MakerAcquireServiceResponse> makers
) {

	public static MakerListAcquireServiceResponse from(List<MakerThemeResponse> makers) {
		return MakerListAcquireServiceResponse.builder()
			.makers(makers.stream().map(MakerAcquireServiceResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	public record MakerAcquireServiceResponse(
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

		public static MakerAcquireServiceResponse from(MakerThemeResponse maker) {
			return MakerAcquireServiceResponse.builder()
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
