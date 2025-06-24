package com.soptie.server.domain.achievement;

import static com.soptie.server.common.utils.ChallengeUtils.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mysema.commons.lang.Pair;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.MemberChallenge;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.adapter.ThemeAdapter;
import com.soptie.server.persistence.adapter.challenge.ChallengeAdapter;
import com.soptie.server.persistence.adapter.challenge.MemberChallengeAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AchievedThemeService {
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberChallengeAdapter memberChallengeAdapter;
	private final ChallengeAdapter challengeAdapter;
	private final ThemeAdapter themeAdapter;

	public Map<Theme, Integer> getAchievedThemes(long memberId) {
		Map<Long /* theme id */, Integer /* achieved count */> achievementMap = new LinkedHashMap<>();

		// achieved routines
		memberRoutineAdapter.findByMemberId(memberId)
			.forEach(routine -> achievementMap.put(
				routine.getThemeId(),
				achievementMap.getOrDefault(routine.getThemeId(), 0) + routine.getAchievementCount()));

		// achieved challenges
		List<MemberChallenge> memberChallenges = memberChallengeAdapter.findAllByMemberId(memberId);
		Map<Long, Challenge> challengesMap = getChallengesMap(challengeAdapter, memberChallenges);

		memberChallenges
			.forEach(memberChallenge -> {
				Challenge challenge = challengesMap.get(memberChallenge.getChallengeId());

				if (challenge == null) {
					return;
				}

				long themeId = challenge.getThemeId();
				achievementMap.merge(themeId, memberChallenge.getAchievedCount(), Integer::sum);
			});

		Map<Long, Theme> themeMap = themeAdapter.findAll()
			.stream().map(it -> Pair.of(it.getId(), it))
			.filter(it -> it.getFirst() != null && it.getSecond() != null)
			.collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

		return achievementMap.entrySet()
			.stream()
			.filter(entry -> entry.getValue() > 0)
			.map(entry -> Pair.of(themeMap.get(entry.getKey()), entry.getValue()))
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

	public Achievement getAchievementTheme(long memberId, long themeId) {
		Theme theme = themeAdapter.findById(themeId);

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
		List<MemberChallenge> memberChallenges = memberChallengeAdapter.findAllByMemberId(memberId);
		Map<Long, Challenge> challengesMap = getChallengesMap(challengeAdapter, memberChallenges);

		List<Long> challengeIds = memberChallengeAdapter.findAllByMemberId(memberId)
			.stream()
			.filter(it -> challengesMap.get(it.getChallengeId()).getThemeId() == themeId)
			.map(MemberChallenge::getId)
			.toList();

		List<AchievedChallenge> achievedChallenges = memberChallengeAdapter.findByIds(challengeIds)
			.stream()
			.filter(Objects::nonNull)
			.filter(it -> challengesMap.containsKey(it.getChallengeId()))
			.sorted((a, b) -> {
				int diff = b.getAchievedCount() - a.getAchievedCount();
				if (diff != 0) {
					return diff;
				}
				Challenge aChallenge = challengesMap.get(a.getChallengeId());
				Challenge bChallenge = challengesMap.get(b.getChallengeId());
				return aChallenge.getContent().compareTo(bChallenge.getContent());
			})
			.map(it -> AchievedChallenge.of(it, challengesMap.get(it.getChallengeId())))
			.toList();

		return Achievement.of(theme, achievedRoutines, achievedChallenges);
	}
}
