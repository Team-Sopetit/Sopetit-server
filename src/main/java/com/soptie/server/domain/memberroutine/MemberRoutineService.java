package com.soptie.server.domain.memberroutine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.request.memberroutine.CreateMemberRoutinesRequest;
import com.soptie.server.api.controller.dto.response.memberroutine.AchieveMemberRoutineResponse;
import com.soptie.server.api.controller.dto.response.memberroutine.CreateMemberRoutinesResponse;
import com.soptie.server.api.controller.dto.response.memberroutine.GetMemberRoutinesResponse;
import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.RoutineAdapter;
import com.soptie.server.persistence.adapter.ThemeAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRoutineService {
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberAdapter memberAdapter;
	private final RoutineAdapter routineAdapter;
	private final ThemeAdapter themeAdapter;

	@Transactional
	public CreateMemberRoutinesResponse createRoutines(
		long memberId,
		CreateMemberRoutinesRequest request
	) {
		val member = memberAdapter.findById(memberId);
		val routines = routineAdapter.findByIds(request.routineIds());
		val savedMemberRoutines = memberRoutineAdapter.saveAll(member, routines);
		return CreateMemberRoutinesResponse.of(savedMemberRoutines);
	}

	public GetMemberRoutinesResponse getMemberRoutines(long memberId) {
		val memberRoutines = memberRoutineAdapter.findByMemberId(memberId);

		val routinesIds = memberRoutines.stream().map(MemberRoutine::getRoutineId).toList();
		val routines = routineAdapter.findByIds(routinesIds);
		val routinesById = routines.stream().collect(Collectors.toMap(Routine::getId, routine -> routine));

		val themeIds = routines.stream().map(Routine::getThemeId).distinct().toList();
		val themes = themeAdapter.findByIds(themeIds);
		val themesById = themes.stream().collect(Collectors.toMap(Theme::getId, theme -> theme));

		val routinesByTheme = toRoutinesByTheme(memberRoutines, routinesById, themesById);
		return GetMemberRoutinesResponse.of(routinesByTheme);
	}

	@Transactional
	public void deleteMemberRoutines(long memberId, List<Long> memberRoutineIds) {
		val memberRoutines = memberRoutineAdapter.findByIds(memberRoutineIds).stream()
			.filter(memberRoutine -> memberRoutine.getMemberId() == memberId)
			.toList();
		memberRoutineAdapter.deleteAll(memberRoutines);
	}

	@Transactional
	public AchieveMemberRoutineResponse achieveMemberRoutine(long memberId, long memberRoutineId) {
		val member = memberAdapter.findById(memberId);
		val memberRoutine = memberRoutineAdapter.findById(memberRoutineId);
		val isAchievedToday = memberRoutine.isAchievedToday();

		if (memberRoutine.getMemberId() != memberId) {
			throw new SoftieException(
				ExceptionCode.NOT_AVAILABLE,
				"Member ID: " + memberId + ", MemberRoutine ID: " + memberRoutineId);
		}

		if (!isAchievedToday) {
			member.getCottonInfo().addBasicCottonCount();
			memberAdapter.update(member);
		}

		memberRoutine.achieve();
		memberRoutineAdapter.update(memberRoutine);

		return AchieveMemberRoutineResponse.of(memberRoutine, !isAchievedToday);
	}

	@Transactional
	public void initAchievement() {
		memberRoutineAdapter.initAllAchievement();
	}

	private Map<Theme, Map<Routine, MemberRoutine>> toRoutinesByTheme(
		List<MemberRoutine> memberRoutines,
		Map<Long, Routine> routinesById,
		Map<Long, Theme> themesById
	) {
		return memberRoutines.stream()
			.collect(Collectors.groupingBy(
				// Theme-key
				memberRoutine -> {
					val themeId = routinesById.get(memberRoutine.getRoutineId()).getThemeId();
					return themesById.get(themeId);
				},

				// Map-value
				Collectors.toMap(
					memberRoutine -> routinesById.get(memberRoutine.getRoutineId()), // routine-key
					memberRoutine -> memberRoutine // memberRoutine-value
				)
			));
	}
}
