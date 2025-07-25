package com.soptie.server.api.controller.calendar.dto;

import static lombok.AccessLevel.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.soptie.server.domain.challenge.ChallengeHistory;
import com.soptie.server.domain.memberroutine.RoutineHistory;
import com.soptie.server.domain.theme.Theme;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.val;

@Builder(access = PRIVATE)
public record DateHistoryResponse(
	@Schema(description = "메모 아이디 (null일 시, 0)", example = "1")
	long memoId,
	@Schema(description = "메모 내용", example = "이것은 메모가 아니다.")
	@NotNull String memoContent,
	@Schema(description = "테마와 달성 기록 정보")
	@NotNull List<HistoriesResponse> histories
) {

	public static DateHistoryResponse of(
		final long memoId,
		final String memoContent,
		final Map<Theme, List<RoutineHistory>> routines,
		final Map<Theme, List<ChallengeHistory>> challenges
	) {
		return DateHistoryResponse.builder()
			.memoId(memoId)
			.memoContent(memoContent)
			.histories(toHistories(routines, challenges))
			.build();
	}

	private static List<HistoriesResponse> toHistories(
		final Map<Theme, List<RoutineHistory>> routines,
		final Map<Theme, List<ChallengeHistory>> challenges
	) {
		val histories = getHistories(routines, challenges);
		return histories.values().stream().toList();
	}

	private static Map<Theme, HistoriesResponse> getHistories(
		final Map<Theme, List<RoutineHistory>> routines,
		final Map<Theme, List<ChallengeHistory>> challenges
	) {
		return Stream.concat(routines.keySet().stream(), challenges.keySet().stream())
			.distinct()
			.sorted(Comparator.comparingInt(Theme::getSequence))
			.collect(Collectors.toMap(
				theme -> theme,
				theme -> HistoriesResponse.of(
					theme,
					routines.getOrDefault(theme, Collections.emptyList()),
					challenges.getOrDefault(theme, Collections.emptyList())
				),
				(existing, replacement) -> existing,
				LinkedHashMap::new
			));
	}

	@Builder(access = PRIVATE)
	private record HistoriesResponse(
		@Schema(description = "테마 id", example = "1")
		long themeId,
		@Schema(description = "테마 이름", example = "관계 쌓기")
		@NotNull String themeName,
		@Schema(description = "달성 기록")
		List<HistoryResponse> histories
	) {

		private static HistoriesResponse of(
			final Theme theme,
			final List<RoutineHistory> routines,
			final List<ChallengeHistory> challenges
		) {
			return HistoriesResponse.builder()
				.themeId(theme.getId())
				.themeName(theme.getName())
				.histories(getHistoryResponse(routines, challenges))
				.build();
		}

		private static List<HistoryResponse> getHistoryResponse(
			final List<RoutineHistory> routines,
			final List<ChallengeHistory> challenges
		) {
			return Stream.concat(
				challenges.stream().map(HistoryResponse::createChallenges),
				routines.stream().map(HistoryResponse::createRoutines)
			).toList();
		}

		@Builder(access = PRIVATE)
		private record HistoryResponse(
			@Schema(description = "기록 id", example = "1")
			long historyId,
			@Schema(description = "루틴 내용", example = "밥 먹기")
			@NotNull String content,
			@Schema(description = "챌린지 여부", example = "true")
			boolean isChallenge
		) {

			private static HistoryResponse createRoutines(final RoutineHistory history) {
				return HistoryResponse.builder()
					.historyId(history.getId())
					.content(history.getContent())
					.isChallenge(false)
					.build();
			}

			private static HistoryResponse createChallenges(final ChallengeHistory history) {
				return HistoryResponse.builder()
					.historyId(history.getId())
					.content(history.getContent())
					.isChallenge(true)
					.build();
			}
		}
	}
}
