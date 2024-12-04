package com.soptie.server.api.controller.dto.response.calendar;

import static lombok.AccessLevel.PRIVATE;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.soptie.server.domain.membermission.MissionHistory;
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
		final Map<Theme, List<MissionHistory>> missions
	) {
		return DateHistoryResponse.builder()
			.memoId(memoId)
			.memoContent(memoContent)
			.histories(toHistories(routines, missions))
			.build();
	}

	private static List<HistoriesResponse> toHistories(
		final Map<Theme, List<RoutineHistory>> routines,
		final Map<Theme, List<MissionHistory>> missions
	) {
		val histories = getHistories(routines, missions);
		return histories.values().stream().toList();
	}

	private static Map<Theme, HistoriesResponse> getHistories(
		final Map<Theme, List<RoutineHistory>> routines,
		final Map<Theme, List<MissionHistory>> missions
	) {
		return Stream.concat(routines.keySet().stream(), missions.keySet().stream())
			.distinct()
			.collect(Collectors.toMap(
				theme -> theme,
				theme -> HistoriesResponse.of(
					theme,
					routines.getOrDefault(theme, Collections.emptyList()),
					missions.getOrDefault(theme, Collections.emptyList())
				)
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
			final List<MissionHistory> missions
		) {
			return HistoriesResponse.builder()
				.themeId(theme.getId())
				.themeName(theme.getName())
				.histories(getHistoryResponse(routines, missions))
				.build();
		}

		private static List<HistoryResponse> getHistoryResponse(
			final List<RoutineHistory> routines,
			final List<MissionHistory> missions
		) {
			return Stream.concat(
				routines.stream().map(HistoryResponse::createRoutines),
				missions.stream().map(HistoryResponse::createMissions)
			).toList();
		}

		@Builder(access = PRIVATE)
		private record HistoryResponse(
			@Schema(description = "기록 id", example = "1")
			long historyId,
			@Schema(description = "루틴 내용", example = "밥 먹기")
			@NotNull String content,
			@Schema(description = "도전 루틴 여부", example = "true")
			boolean isMission
		) {

			private static HistoryResponse createRoutines(final RoutineHistory history) {
				return HistoryResponse.builder()
					.historyId(history.getId())
					.content(history.getContent())
					.isMission(false)
					.build();
			}

			private static HistoryResponse createMissions(final MissionHistory history) {
				return HistoryResponse.builder()
					.historyId(history.getId())
					.content(history.getContent())
					.isMission(true)
					.build();
			}
		}
	}
}
