package com.soptie.server.api.controller.dto.response.maker;

import static lombok.AccessLevel.*;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder(access = PRIVATE)
public record GetMakerListResponse(
	@Schema(description = "메아커 테마 정보 목록")
	@NotNull List<GetMakerResponse> makers
) {

	public static GetMakerListResponse from(List<MakerThemeResponse> response) {
		return GetMakerListResponse.builder()
			.makers(response.stream().map(GetMakerResponse::from).toList())
			.build();
	}

	@Builder(access = PRIVATE)
	private record GetMakerResponse(
		@Schema(description = "메이커 id", example = "1")
		long makerId,
		@Schema(description = "메이커 소개 페이지 url", example = "https://www.test")
		@NotNull String content,
		@Schema(description = "메이커 이름", example = "소프티")
		@NotNull String name,
		@Schema(description = "메이커 프로필 이미지 url", example = "https://www.test")
		@NotNull String profileImageUrl,
		@Schema(description = "메이커 태그 목록", example = "[\"소프티한\", \"행복한\", \"유능한\"]")
		@NotNull List<String> tags,
		@Schema(description = "테마 id", example = "1")
		long themeId,
		@Schema(description = "테마 설명", example = "소프티하려면 ...더보기")
		@NotNull String description,
		@Schema(description = "테마 이름", example = "소프티해지기")
		@NotNull String themeName,
		@Schema(description = "수식어", example = "200만 유튜버와 함께 하는")
		@NotNull String modifier
	) {

		public static GetMakerResponse from(MakerThemeResponse response) {
			return GetMakerResponse.builder()
				.makerId(response.maker().getId())
				.content(response.maker().getIntroductionUrl())
				.name(response.maker().getName())
				.profileImageUrl(response.maker().getProfileImageUrl())
				.tags(response.maker().getTags().toTagList())
				.themeId(response.theme().getId())
				.description(response.theme().getDescription())
				.themeName(response.theme().getName())
				.modifier(response.theme().getComment())
				.build();
		}
	}
}
