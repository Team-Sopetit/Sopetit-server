package com.soptie.server.routine.service;

import static com.soptie.server.common.config.ValueConfig.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberroutine.adapter.MemberRoutineFinder;
import com.soptie.server.routine.adapter.ChallengeFinder;
import com.soptie.server.routine.adapter.RoutineFinder;
import com.soptie.server.routine.entity.Challenge;
import com.soptie.server.routine.entity.RoutineType;
import com.soptie.server.routine.service.dto.request.DailyRoutineListByThemeGetServiceRequest;
import com.soptie.server.routine.service.dto.request.DailyRoutineListByThemesGetServiceRequest;
import com.soptie.server.routine.service.dto.request.HappinessRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.request.HappinessSubRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.response.DailyRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessRoutineListGetServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessSubRoutineListGetServiceResponse;
import com.soptie.server.routine.service.vo.ChallengeVO;
import com.soptie.server.routine.service.vo.RoutineVO;
import com.soptie.server.theme.adapter.ThemeFinder;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {

	private final RoutineFinder routineFinder;
	private final ThemeFinder themeFinder;
	private final MemberFinder memberFinder;
	private final ChallengeFinder challengeFinder;
	private final MemberRoutineFinder memberRoutineFinder;

	public DailyRoutineListGetServiceResponse getRoutinesByThemes(DailyRoutineListByThemesGetServiceRequest request) {
		val routines = routineFinder.findDailyRoutinesByThemeIds(request.themeIds());
		return DailyRoutineListGetServiceResponse.of(routines);
	}

	public DailyRoutineListGetServiceResponse getRoutinesByTheme(DailyRoutineListByThemeGetServiceRequest request) {
		val theme = themeFinder.findById(request.themeId());
		val member = memberFinder.findById(request.memberId());
		val routines = routineFinder.findDailyRoutinesByThemeAndNotMember(theme, member);
		return DailyRoutineListGetServiceResponse.of(routines, theme);
	}

	public HappinessRoutineListGetServiceResponse getHappinessRoutinesByTheme(
		HappinessRoutineListGetServiceRequest request
	) {
		val routines = routineFinder.findChallengeRoutinesByTheme(request.themeId());
		return HappinessRoutineListGetServiceResponse.of(routines);
	}

	public HappinessSubRoutineListGetServiceResponse getHappinessSubRoutines(
		HappinessSubRoutineListGetServiceRequest request
	) {
		val routine = routineFinder.findById(request.routineId());
		val subRoutines = challengeFinder.findByRoutine(routine);
		return HappinessSubRoutineListGetServiceResponse.of(routine, subRoutines);
	}

	public Map<Long, List<RoutineVO>> acquireAllInDailyWithThemeId(Set<Long> themeIds) {
		val themeToRoutine = new LinkedHashMap<Long, List<RoutineVO>>();
		for (val themeId : themeIds) {
			val routines = routineFinder.findAllByTypeAndThemeId(RoutineType.DAILY, themeId);
			themeToRoutine.put(themeId, routines);
		}
		return themeToRoutine;
	}

	public Map<String, List<ChallengeVO>> acquireAllInChallengeWithThemeId(long memberId, long themeId) {
		themeFinder.findById(themeId);
		val member = memberFinder.findById(memberId);
		val challengeIdByMember = getChallengeIdByMember(member);
		val challengeRoutinesByTheme = routineFinder.findChallengeRoutinesByTheme(themeId);
		val themeToChallenge = new LinkedHashMap<String, List<ChallengeVO>>();
		for (val routine : challengeRoutinesByTheme) {
			val challenges = challengeFinder.findByRoutine(routine);
			themeToChallenge.put(routine.getContent(), getChallengeVOs(challenges, challengeIdByMember));
		}
		return themeToChallenge;
	}

	private long getChallengeIdByMember(Member member) {
		val challengeByMember = memberRoutineFinder.findChallengeByMember(member);
		if (challengeByMember.isPresent()) {
			return challengeByMember.get().challengeId();
		}
		return MEMBER_HAS_NOT_CHALLENGE;
	}

	private List<ChallengeVO> getChallengeVOs(List<Challenge> challenges, long challengeId) {
		return challenges.stream()
			.map(challenge -> ChallengeVO.from(challenge, challengeId))
			.toList();
	}
}
