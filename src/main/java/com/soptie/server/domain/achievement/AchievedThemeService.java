package com.soptie.server.domain.achievement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mysema.commons.lang.Pair;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.MemberChallenge;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.adapter.challenge.MemberChallengeAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.global.ChallengeStore;
import com.soptie.server.persistence.global.RoutineStore;
import com.soptie.server.persistence.global.ThemeStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AchievedThemeService {
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberChallengeAdapter memberChallengeAdapter;

	private final ThemeStore themeStore;
	private final ChallengeStore challengeStore;
	private final RoutineStore routineStore;

	public Map<Theme, Integer> getAchievedThemes(long memberId) {
		Map<Long /* theme id */, Integer /* achieved count */> achievementMap = new LinkedHashMap<>();

		// achieved routines
		memberRoutineAdapter.findByMemberId(memberId)
			.forEach(routine -> achievementMap.put(
				routine.getThemeId(),
				achievementMap.getOrDefault(routine.getThemeId(), 0) + routine.getAchievementCount()));

		// achieved challenges
		memberChallengeAdapter.findAllByMemberId(memberId)
			.forEach(memberChallenge -> {
				Challenge challenge = challengeStore.get(memberChallenge.getChallengeId());
				if (challenge != null) {
					long themeId = challenge.getThemeId();
					achievementMap.put(
						themeId,
						achievementMap.getOrDefault(themeId, 0) + memberChallenge.getAchievedCount());
				}
			});

		return achievementMap.entrySet()
			.stream()
			.filter(entry -> entry.getValue() > 0)
			.map(entry -> Pair.of(themeStore.get(entry.getKey()), entry.getValue()))
			.filter(pair -> pair.getFirst() != null && pair.getSecond() != null)
			.sorted((a, b) -> { // 달성횟수 높은 순으로, 같으면 테마 이름 순으로
				int diff = b.getSecond() - a.getSecond();
				return diff != 0 ? diff : a.getFirst().getName().compareTo(b.getFirst().getName());
			})
			.collect(Collectors.toMap(
				Pair::getFirst,
				Pair::getSecond,
				(v1, v2) -> v1, // key 충돌 시 첫 값 유지
				LinkedHashMap::new // 순서 유지
			));
	}

	//todo. 커스텀 루틴 반영되는지 api test
	public Achievement getAchievementTheme(long memberId, long themeId) {
		Theme theme = themeStore.get(themeId);

		// achieved routines
		List<Long> routineIds = memberRoutineAdapter.findByMemberId(memberId)
			.stream()
			.filter(it -> it.getThemeId() == themeId)
			.map(MemberRoutine::getId)
			.toList();

		List<AchievedRoutine> achievedRoutines = memberRoutineAdapter.findByIds(routineIds)
			.stream()
			.sorted((a, b) -> {
				int diff = b.getAchievementCount() - a.getAchievementCount();
				return diff != 0 ? diff : a.getContent().compareTo(b.getContent());
			})
			.map(AchievedRoutine::from)
			.toList();

		// achieved challenges
		List<Long> challengeIds = memberChallengeAdapter.findAllByMemberId(memberId)
			.stream()
			.filter(it -> challengeStore.get(it.getChallengeId()).getThemeId() == themeId)
			.map(MemberChallenge::getId)
			.toList();

		List<AchievedChallenge> achievedChallenges = memberChallengeAdapter.findByIds(challengeIds)
			.stream()
			.sorted((a, b) -> {
				int diff = b.getAchievedCount() - a.getAchievedCount();
				if (diff != 0) {
					return diff;
				}
				Challenge aChallenge = challengeStore.get(a.getChallengeId());
				Challenge bChallenge = challengeStore.get(b.getChallengeId());
				return aChallenge.getContent().compareTo(bChallenge.getContent());
			})
			.map(it -> AchievedChallenge.of(it, challengeStore.get(it.getChallengeId())))
			.toList();

		return Achievement.of(theme, achievedRoutines, achievedChallenges);
	}
}
