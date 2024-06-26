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
import com.soptie.server.memberroutine.repository.dto.MemberChallengeResponse;
import com.soptie.server.routine.adapter.ChallengeFinder;
import com.soptie.server.routine.adapter.RoutineFinder;
import com.soptie.server.routine.entity.RoutineType;
import com.soptie.server.routine.service.dto.request.HappinessSubRoutineListGetServiceRequest;
import com.soptie.server.routine.service.dto.response.ChallengeRoutineListAcquireServiceResponse;
import com.soptie.server.routine.service.dto.response.HappinessSubRoutineListGetServiceResponse;
import com.soptie.server.routine.service.vo.RoutineVO;
import com.soptie.server.theme.adapter.ThemeFinder;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {

	private final RoutineFinder routineFinder;
	private final ChallengeFinder challengeFinder;
	private final MemberRoutineFinder memberRoutineFinder;
	private final ThemeFinder themeFinder;
	private final MemberFinder memberFinder;

	public List<RoutineVO> acquireAllInDailyByThemeIds(List<Long> themeIds) {
		return routineFinder.findAllByTypeAndThemeIds(RoutineType.DAILY, themeIds);
	}

	public List<RoutineVO> acquireAllInDailyNotInMemberByThemeId(long memberId, long themeId) {
		return routineFinder.findAllNotInMemberByTypeAndThemeId(memberId, RoutineType.DAILY, themeId);
	}

	public List<RoutineVO> acquireAllInHappinessByThemeId(Long themeId) {
		return routineFinder.findAllByTypeAndThemeId(RoutineType.CHALLENGE, themeId);
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

	public Map<String, ChallengeRoutineListAcquireServiceResponse> acquireAllInChallengeWithThemeId(
		long memberId,
		long themeId
	) {
		themeFinder.isExistById(themeId);
		val member = memberFinder.findById(memberId);
		val challengeIdByMember = getChallengeIdByMember(member);
		val challengeRoutinesByTheme = routineFinder.findChallengeRoutinesByTheme(themeId);
		val themeToChallenge = new LinkedHashMap<String, ChallengeRoutineListAcquireServiceResponse>();
		for (val routine : challengeRoutinesByTheme) {
			val challenges = challengeFinder.findByRoutine(routine);
			themeToChallenge.put(routine.getContent(),
				ChallengeRoutineListAcquireServiceResponse.of(challenges, challengeIdByMember));
		}
		return themeToChallenge;
	}

	private long getChallengeIdByMember(Member member) {
		val challengeByMember = memberRoutineFinder.findChallengeByMember(member);
		return challengeByMember.map(MemberChallengeResponse::challengeId).orElse(MEMBER_HAS_NOT_CHALLENGE);
	}
}
