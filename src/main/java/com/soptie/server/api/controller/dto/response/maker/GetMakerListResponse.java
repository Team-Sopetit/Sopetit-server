package com.soptie.server.api.controller.dto.response.maker;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import com.soptie.server.domain.maker.Tags;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record GetMakerListResponse(
	@NotNull List<GetMakerResponse> makers
) {

	public static GetMakerListResponse from(List<MakerThemeResponse> response) {
		return GetMakerListResponse.builder()
			.makers(response.stream().map(GetMakerResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record GetMakerResponse(
		long themeId,
		@NotNull String description,
		@NotNull String name,
		@NotNull String comment,
		long makerId,
		@NotNull String makerName,
		@NotNull String introductionUrl,
		@NotNull String profileImageUrl,
		@NotNull Tags tags
	) {

		public static GetMakerResponse from(MakerThemeResponse response) {
			return GetMakerResponse.builder()
				.themeId(response.themeId())
				.description(response.description())
				.name(response.name())
				.comment(response.comment())
				.makerId(response.makerId())
				.makerName(response.makerName())
				.introductionUrl(response.introductionUrl())
				.profileImageUrl(response.profileImageUrl())
				.tags(response.tags())
				.build();
		}
	}
}
