package com.soptie.server.domain.routine;

import static com.soptie.server.common.support.ValueConfig.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.response.routine.GetRoutinesByMemberResponse;
import com.soptie.server.api.controller.dto.response.routine.GetRoutinesByThemeResponse;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.persistence.adapter.ChallengeFinder;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemberRoutineFinder;
import com.soptie.server.persistence.adapter.RoutineAdapter;
import com.soptie.server.persistence.adapter.ThemeAdapter;
import com.soptie.server.persistence.repository.dto.MemberChallengeResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineService {
	private final RoutineAdapter routineAdapter;
	private final ChallengeFinder challengeFinder;
	private final MemberRoutineFinder memberRoutineFinder;
	private final ThemeAdapter themeAdapter;
	private final MemberAdapter memberAdapter;

	public GetRoutinesByThemeResponse getRoutinesByThemeIds(Set<Long> themeIds) {
		val routinesByThemeId = new LinkedHashMap<Long, List<Routine>>();
		for (val themeId : themeIds) {
			val routines = routineAdapter.findByThemeId(themeId);
			routinesByThemeId.put(themeId, routines);
		}
		return GetRoutinesByThemeResponse.of(routinesByThemeId);
	}

	public GetRoutinesByMemberResponse getRoutinesByThemeId(long memberId, long themeId) {
		val routines = routineAdapter.findByThemeId(themeId);
		val memberRoutineIds = memberRoutineFinder.findByMemberId(memberId).stream()
			.map(MemberRoutine::getRoutineId)
			.toList();
		return GetRoutinesByMemberResponse.of(getRoutineToMember(routines, memberRoutineIds));
	}

	private Map<Boolean, List<Routine>> getRoutineToMember(List<Routine> routines, List<Long> memberRoutineIds) {
		val routineToMember = new HashMap<Boolean, List<Routine>>();
		routineToMember.put(true, new ArrayList<>());
		routineToMember.put(false, new ArrayList<>());
		for (val routine : routines) {
			val isMemberRoutine = memberRoutineIds.contains(routine.getId());
			routineToMember.get(isMemberRoutine).add(routine);
		}
		return routineToMember;
	}

	public Map<String, ChallengeRoutineListAcquireServiceResponse> acquireAllInChallengeWithThemeId(
		long memberId,
		long themeId
	) {
		themeAdapter.isExistById(themeId);
		val member = memberAdapter.findById(memberId);
		val challengeIdByMember = getChallengeIdByMember(member);
		val challengeRoutinesByTheme = routineAdapter.findChallengeRoutinesByTheme(themeId);
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
