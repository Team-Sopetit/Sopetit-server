package com.soptie.server.domain.achievement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.soptie.server.api.controller.dto.response.achievement.AchievedThemesResponse;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.MemberChallenge;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.adapter.ThemeAdapter;
import com.soptie.server.persistence.adapter.challenge.ChallengeAdapter;
import com.soptie.server.persistence.adapter.challenge.MemberChallengeAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class AchievedThemeService {
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberChallengeAdapter memberChallengeAdapter;
	private final RoutineAdapter routineAdapter;
	private final ChallengeAdapter challengeAdapter;
	private final ThemeAdapter themeAdapter;

	//TODO: 코드 개판인 거 알고요.. 커스텀 때 디비 구조 변경하면 리팩 좀 깔끔하게 할 수 있을 것 같아, 때 맞춰 진행하겟슴다
	public AchievedThemesResponse getAchievedThemes(long memberId) {
		val achievedRoutines = memberRoutineAdapter.findByMemberId(memberId);
		val routineIds = achievedRoutines.stream().map(MemberRoutine::getRoutineId).toList();
		val routines = routineAdapter.findByIds(routineIds);

		val achievedChallenges = memberChallengeAdapter.findAllByMemberId(memberId);
		val challengeIds = achievedChallenges.stream().map(MemberChallenge::getChallengeId).toList();
		val challenges = challengeAdapter.findByIds(challengeIds);

		val themeIds = new HashSet<Long>();
		themeIds.addAll(routines.stream().map(Routine::getThemeId).toList());
		themeIds.addAll(challenges.stream().map(Challenge::getThemeId).toList());
		val themes = themeAdapter.findByIds(themeIds.stream().toList());

		val achievedCountsByTheme = new HashMap<Long, Integer>();

		val routineMap = getRoutineMapOfMember(routines, achievedRoutines);
		for (val memberRoutine : achievedRoutines) {
			val themeId = routineMap.get(memberRoutine.getId()).getThemeId();
			achievedCountsByTheme.put(
				themeId,
				achievedCountsByTheme.getOrDefault(themeId, 0) + memberRoutine.getAchievementCount());
		}

		val challengeMap = getChallengeMapOfMember(challenges, achievedChallenges);
		for (val memberChallenge : achievedChallenges) {
			val themeId = challengeMap.get(memberChallenge.getId()).getThemeId();
			achievedCountsByTheme.put(
				themeId,
				achievedCountsByTheme.getOrDefault(themeId, 0) + memberChallenge.getAchievedCount());
		}

		return AchievedThemesResponse.of(themes, achievedCountsByTheme);
	}

	private Map<Long, Challenge> getChallengeMapOfMember(
		List<Challenge> challenges,
		List<MemberChallenge> achievedChallenges
	) {
		val challengeByIdMap = challenges.stream()
			.collect(Collectors.toMap(Challenge::getId, Function.identity()));
		val challengeMap = new HashMap<Long, Challenge>();
		for (val memberChallenge : achievedChallenges) {
			val challenge = challengeByIdMap.get(memberChallenge.getChallengeId());
			if (challenge != null) {
				challengeMap.put(memberChallenge.getId(), challenge);
			}
		}
		return challengeMap;
	}

	private Map<Long, Routine> getRoutineMapOfMember(
		List<Routine> routines,
		List<MemberRoutine> achievedRoutines
	) {
		val routineByIdMap = routines.stream()
			.collect(Collectors.toMap(Routine::getId, Function.identity()));
		val routineMap = new HashMap<Long, Routine>();
		for (val memberRoutine : achievedRoutines) {
			val routine = routineByIdMap.get(memberRoutine.getRoutineId());
			if (routine != null) {
				routineMap.put(memberRoutine.getId(), routine);
			}
		}
		return routineMap;
	}
}
