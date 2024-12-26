package com.soptie.server.api.controller.dto.response.memberchallenge;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.MemberChallenge;
import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MemberChallengeResponse(
	@Schema(description = "회원 챌린지 id", example = "1")
	long memberChallengeId,
	@Schema(description = "테마 정보")
	ThemeResponse theme,
	@Schema(description = "챌린지 내용", example = "일어나면 5분 안에 이불 개기")
	@NotNull String content,
	@Schema(description = "챌린지 상세 내용", example = "평소에 바빠서 ...더보기")
	@NotNull String description,
	@Schema(description = "장소", example = "침대")
	@NotNull String place,
	@Schema(description = "소요시간", example = "3분")
	@NotNull String timeTaken
) {

	public static MemberChallengeResponse of(MemberChallenge memberChallenge, Theme theme, Challenge challenge) {
		return MemberChallengeResponse.builder()
			.memberChallengeId(memberChallenge.getId())
			.theme(ThemeResponse.of(theme))
			.content(challenge.getContent())
			.description(challenge.getDescription())
			.place(challenge.getPlace())
			.timeTaken(challenge.getRequiredTime())
			.build();
	}

	@Builder(access = AccessLevel.PRIVATE)
	private record ThemeResponse(
		@Schema(description = "테마 id", example = "1")
		long themeId,
		@Schema(description = "테마 이름", example = "관계 쌓기")
		@NotNull String themeName
	) {

		private static ThemeResponse of(Theme theme) {
			return ThemeResponse.builder()
				.themeId(theme.getId())
				.themeName(theme.getName())
				.build();
		}
	}
}
