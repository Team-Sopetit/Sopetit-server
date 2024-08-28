package com.soptie.server.temporary;

import java.security.Principal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.adapter.ChallengeAdapter;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.MissionAdapter;
import com.soptie.server.persistence.adapter.RoutineAdapter;
import com.soptie.server.persistence.adapter.ThemeAdapter;
import com.soptie.server.persistence.entity.ChallengeEntity;
import com.soptie.server.persistence.entity.ThemeEntity;
import com.soptie.server.persistence.repository.ChallengeRepository;
import com.soptie.server.persistence.repository.ThemeRepository;
import com.soptie.server.temporary.dto.CreateMemberDailyRoutine;
import com.soptie.server.temporary.dto.GetHappinessRoutinesResponse;
import com.soptie.server.temporary.dto.GetHappinessSubRoutinesResponse;
import com.soptie.server.temporary.dto.GetMemberDailyRoutinesResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TemporaryApi {
	private final ThemeAdapter themeAdapter;
	private final ChallengeAdapter challengeAdapter;
	private final MissionAdapter missionAdapter;
	private final ThemeRepository themeRepository;
	private final ChallengeRepository challengeRepository;
	private final RoutineAdapter routineAdapter;
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberAdapter memberAdapter;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routines/happiness/routine/{routineId}")
	public SuccessResponse<GetHappinessSubRoutinesResponse> getHappinessRoutinesByRoutine(
		@PathVariable long routineId
	) {
		val challenge = challengeAdapter.findById(routineId);
		val theme = themeAdapter.findById(challenge.getThemeId());
		val missions = missionAdapter.findByChallengeIds(List.of(challenge.getId()));
		return SuccessResponse.success(
			"루틴별 행복 서브 루틴 내용 조회 성공",
			GetHappinessSubRoutinesResponse.of(theme, challenge, missions));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routines/happiness")
	public SuccessResponse<GetHappinessRoutinesResponse> getHappinessRoutinesByTheme(
		@RequestParam(required = false) Long themeId
	) {
		val themes = themeId == null
			? themeRepository.findAll().stream().map(ThemeEntity::toDomain).toList()
			: List.of(themeAdapter.findById(themeId));
		val challenges = challengeRepository.findAll().stream().map(ChallengeEntity::toDomain).toList();

		val map = themes.stream()
			.collect(Collectors.toMap(
				theme -> theme,
				theme -> challenges.stream()
					.filter(challenge -> challenge.getThemeId() == theme.getId())
					.toList()
			));

		return SuccessResponse.success(
			"테마 목록별 행복 루틴 목록 조회 성공",
			GetHappinessRoutinesResponse.of(map));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/routines/daily/member")
	@Transactional
	public SuccessResponse<CreateMemberDailyRoutine> createMemberDailyRoutine(
		Principal principal,
		@RequestBody CreateMemberDailyRoutine request
	) {
		val memberId = Long.parseLong(principal.getName());
		val member = memberAdapter.findById(memberId);
		val routine = routineAdapter.findById(request.routineId());
		val memberRoutine = memberRoutineAdapter.saveAll(member, List.of(routine));
		return SuccessResponse.success(
			"데일리 루틴 추가 성공",
			new CreateMemberDailyRoutine(!memberRoutine.isEmpty() ? memberRoutine.get(0).getId() : 0L));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routines/daily/member")
	public SuccessResponse<GetMemberDailyRoutinesResponse> getMemberDailyRoutines(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		val memberRoutines = memberRoutineAdapter.findByMemberId(memberId);
		val routines = routineAdapter.findByIds(memberRoutines.stream().map(MemberRoutine::getRoutineId).toList());

		val routineMap = routines.stream()
			.collect(Collectors.toMap(Routine::getId, Function.identity()));

		val memberRoutineMap = memberRoutines.stream()
			.collect(Collectors.toMap(
				Function.identity(),  // Key: MemberRoutine 자체
				memberRoutine -> routineMap.get(memberRoutine.getRoutineId())  // Value: 해당 MemberRoutine의 Routine
			));

		return SuccessResponse.success(
			"회원의 데일리 루틴 목록 조회 성공",
			GetMemberDailyRoutinesResponse.of(memberRoutineMap));
	}
}
