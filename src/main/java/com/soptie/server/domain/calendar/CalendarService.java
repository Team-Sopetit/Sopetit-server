package com.soptie.server.domain.calendar;

import static com.soptie.server.common.support.ValueConfig.BLANK;
import static com.soptie.server.common.support.ValueConfig.MEMBER_HAS_NOT_MEMO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.response.calendar.DateHistoryResponse;
import com.soptie.server.domain.membermission.MissionHistory;
import com.soptie.server.domain.memberroutine.RoutineHistory;
import com.soptie.server.domain.memo.Memo;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemoAdapter;
import com.soptie.server.persistence.adapter.ThemeAdapter;
import com.soptie.server.persistence.adapter.mission.ChallengeAdapter;
import com.soptie.server.persistence.adapter.mission.MissionAdapter;
import com.soptie.server.persistence.adapter.mission.MissionHistoryAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineHistoryAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarService {

	private final MemberAdapter memberAdapter;
	private final MemoAdapter memoAdapter;
	private final RoutineHistoryAdapter routineHistoryAdapter;
	private final MissionHistoryAdapter missionHistoryAdapter;
	private final ThemeAdapter themeAdapter;
	private final RoutineAdapter routineAdapter;
	private final MissionAdapter missionAdapter;
	private final ChallengeAdapter challengeAdapter;

	public Map<LocalDate, DateHistoryResponse> getCalendar(final long memberId, final int year, final int month) {
		memberAdapter.findById(memberId);
		val startDateTime = LocalDateTime.of(year, month, 1, 0, 0);
		val endDateTime = startDateTime.plusMonths(1).withDayOfMonth(1).minusSeconds(1);
		val memos = getMemos(memberId, year, month);
		val routines = getRoutines(memberId, startDateTime, endDateTime);
		val missions = getMissions(memberId, startDateTime, endDateTime);
		return getHistories(memos, routines, missions);
	}

	private Map<LocalDate, Memo> getMemos(final long memberId, final int year, final int month) {
		val startDate = LocalDate.of(year, month, 1);
		val endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
		val memos = memoAdapter.findAllByMemberIdAndAchievedDateBetween(memberId, startDate, endDate);
		return memos.stream()
			.collect(
				Collectors.toMap(Memo::getAchievedDate, memo -> memo)
			);
	}

	private Map<LocalDate, List<RoutineHistory>> getRoutines(
		final long memberId,
		final LocalDateTime startDateTime,
		final LocalDateTime endDateTime
	) {
		val routines = routineHistoryAdapter.findAllByMemberIdAndCreatedAtBetween(memberId, startDateTime, endDateTime);
		return routines.stream()
			.collect(Collectors.groupingBy(
				routine -> routine.getCreatedAt().toLocalDate(),
				Collectors.mapping(
					routine -> routine,
					Collectors.toUnmodifiableList()
				)
			));
	}

	private Map<LocalDate, List<MissionHistory>> getMissions(
		final long memberId,
		final LocalDateTime startDateTime,
		final LocalDateTime endDateTime
	) {
		val missions = missionHistoryAdapter.findAllByMemberIdAndCreatedAtBetween(memberId, startDateTime, endDateTime);
		return missions.stream()
			.collect(Collectors.groupingBy(
				mission -> mission.getCreatedAt().toLocalDate(),
				Collectors.mapping(
					mission -> mission,
					Collectors.toUnmodifiableList()
				)
			));
	}

	private Map<LocalDate, DateHistoryResponse> getHistories(
		final Map<LocalDate, Memo> memos,
		final Map<LocalDate, List<RoutineHistory>> routines,
		final Map<LocalDate, List<MissionHistory>> missions
	) {
		val dates = getDates(memos, routines, missions);
		return getDateAndHistories(dates, memos, routines, missions);
	}

	private Set<LocalDate> getDates(
		final Map<LocalDate, Memo> memos,
		final Map<LocalDate, List<RoutineHistory>> routines,
		final Map<LocalDate, List<MissionHistory>> missions
	) {
		return Stream.of(memos.keySet(), routines.keySet(), missions.keySet())
			.flatMap(Set::stream)
			.collect(Collectors.toUnmodifiableSet());
	}

	private Map<LocalDate, DateHistoryResponse> getDateAndHistories(
		final Set<LocalDate> dates,
		final Map<LocalDate, Memo> memos,
		final Map<LocalDate, List<RoutineHistory>> routines,
		final Map<LocalDate, List<MissionHistory>> missions
	) {
		return dates.stream()
			.collect(Collectors.toMap(
				date -> date,
				date -> getDateHistory(date, memos, routines, missions)
			));
	}

	private DateHistoryResponse getDateHistory(
		final LocalDate date,
		final Map<LocalDate, Memo> memos,
		final Map<LocalDate, List<RoutineHistory>> routines,
		final Map<LocalDate, List<MissionHistory>> missions
	) {
		val memo = memos.get(date);
		val routineHistories = getRoutines(date, routines);
		val missionHistories = getMissions(date, missions);
		val memoId = memo == null ? MEMBER_HAS_NOT_MEMO : memo.getId();
		val content = memo == null ? BLANK : memo.getContent();
		return DateHistoryResponse.of(memoId, content, routineHistories, missionHistories);
	}

	private Map<Theme, List<RoutineHistory>> getRoutines(
		final LocalDate date,
		final Map<LocalDate, List<RoutineHistory>> routines
	) {
		val histories = routines.getOrDefault(date, Collections.emptyList());
		return histories.stream()
			.collect(Collectors.groupingBy(
				this::getRoutineTheme
			));
	}

	private Theme getRoutineTheme(final RoutineHistory history) {
		val routine = routineAdapter.findById(history.getRoutineId());
		return themeAdapter.findById(routine.getThemeId());
	}

	private Map<Theme, List<MissionHistory>> getMissions(
		final LocalDate date,
		final Map<LocalDate, List<MissionHistory>> missions
	) {
		val histories = missions.getOrDefault(date, Collections.emptyList());
		return histories.stream()
			.collect(Collectors.groupingBy(
				this::getMissionTheme
			));
	}

	private Theme getMissionTheme(final MissionHistory history) {
		val mission = missionAdapter.findById(history.getMissionId());
		val challenge = challengeAdapter.findById(mission.getChallengeId());
		return themeAdapter.findById(challenge.getThemeId());
	}
}
