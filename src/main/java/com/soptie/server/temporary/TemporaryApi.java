package com.soptie.server.temporary;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.soptie.server.domain.doll.DollType;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.ThemeAdapter;
import com.soptie.server.persistence.adapter.mission.ChallengeAdapter;
import com.soptie.server.persistence.adapter.mission.MemberMissionAdapter;
import com.soptie.server.persistence.adapter.mission.MissionAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAdapter;
import com.soptie.server.persistence.entity.ThemeEntity;
import com.soptie.server.persistence.entity.mission.ChallengeEntity;
import com.soptie.server.persistence.repository.ThemeRepository;
import com.soptie.server.persistence.repository.mission.ChallengeRepository;
import com.soptie.server.temporary.dto.CreateMemberDailyRoutine;
import com.soptie.server.temporary.dto.DailyRoutineThemesResponse;
import com.soptie.server.temporary.dto.DailyRoutinesResponse;
import com.soptie.server.temporary.dto.DollResponse;
import com.soptie.server.temporary.dto.GetDailyRoutinesResponse;
import com.soptie.server.temporary.dto.GetHappinessRoutinesResponse;
import com.soptie.server.temporary.dto.GetHappinessSubRoutinesResponse;
import com.soptie.server.temporary.dto.GetMemberDailyRoutinesResponse;
import com.soptie.server.temporary.dto.HappinessRoutineThemesResponse;
import com.soptie.server.temporary.dto.MemberHappinessRoutinesResponse;

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
	private final MemberMissionAdapter memberMissionAdapter;

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

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routines/happiness/themes")
	public SuccessResponse<HappinessRoutineThemesResponse> getHappinessRoutineThemes() {
		val themes = themeRepository.findAll().stream().map(ThemeEntity::toDomain).toList();
		return SuccessResponse.success(
			"행복 루틴 테마 목록 조회 성공",
			HappinessRoutineThemesResponse.of(themes));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routines/daily")
	public SuccessResponse<DailyRoutinesResponse> getDailyRoutines(@RequestParam List<Long> themes) {
		val routines = new ArrayList<Routine>();
		for (val themeId : themes) {
			routines.addAll(routineAdapter.findByThemeId(themeId));
		}
		return SuccessResponse.success(
			"테마 목록별 데일리 루틴 목록 조회 성공",
			DailyRoutinesResponse.of(routines));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routines/daily/theme/{themeId}")
	public SuccessResponse<GetDailyRoutinesResponse> getDailyRoutines(@PathVariable long themeId) {
		val routines = routineAdapter.findByThemeId(themeId);
		return SuccessResponse.success(
			"테마별 데일리 루틴 목록 조회 성공",
			GetDailyRoutinesResponse.of(themeId, routines));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routines/daily/themes")
	public SuccessResponse<DailyRoutineThemesResponse> getDailyRoutineThemes() {
		val themes = themeAdapter.findByBasic();
		return SuccessResponse.success(
			"테마별 데일리 루틴 목록 조회 성공",
			DailyRoutineThemesResponse.of(themes));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/dolls/image/{type}")
	public SuccessResponse<DollResponse> getDolls(@PathVariable DollType type) {
		return SuccessResponse.success(
			"인형 이미지 불러오기 성공",
			DollResponse.of(type));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/routines/happiness/member")
	public ResponseEntity<?> getDolls(Principal principal) {
		val memberId = Long.parseLong(principal.getName());
		return memberMissionAdapter.findByMember(memberId)
			.map(it -> {
				val mission = missionAdapter.findById(it.getMissionId());
				val challenge = challengeAdapter.findById(mission.getChallengeId());
				val theme = themeAdapter.findById(challenge.getThemeId());
				return ResponseEntity.ok(SuccessResponse.success(
					"인형 이미지 불러오기 성공",
					MemberHappinessRoutinesResponse.of(it, challenge, mission, theme)));
			})
			.orElseGet(() -> ResponseEntity.noContent().build());
	}
}
