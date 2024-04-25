package com.soptie.server.memberRoutine.service.daily;

import static com.soptie.server.member.message.ErrorCode.*;
import static com.soptie.server.routine.message.ErrorCode.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.entity.daily.CompletedMemberDailyRoutine;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.repository.CompletedMemberDailyRoutineRepository;
import com.soptie.server.memberRoutine.repository.MemberDailyRoutineRepository;
import com.soptie.server.memberRoutine.service.daily.dto.request.MemberDailyRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.daily.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.daily.dto.request.MemberDailyRoutineDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.daily.dto.request.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.memberRoutine.service.daily.dto.response.MemberDailyRoutineAchieveServiceResponse;
import com.soptie.server.memberRoutine.service.daily.dto.response.MemberDailyRoutineCreateServiceResponse;
import com.soptie.server.memberRoutine.service.daily.dto.response.MemberDailyRoutineListGetServiceResponse;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDailyRoutineServiceImpl implements MemberDailyRoutineService {

	private final MemberDailyRoutineRepository memberDailyRoutineRepository;
	private final MemberRepository memberRepository;
	private final DailyRoutineRepository dailyRoutineRepository;
	private final CompletedMemberDailyRoutineRepository completedMemberDailyRoutineRepository;

	@Override
	@Transactional
	public MemberDailyRoutineCreateServiceResponse createMemberDailyRoutine(MemberDailyRoutineCreateServiceRequest request) {
		val member = findMember(request.memberId());
		member.checkDailyRoutineAddition();
		val routine = findRoutine(request.routineId());
		val savedMemberRoutine = getMemberDailyRoutine(member, routine);
		return MemberDailyRoutineCreateServiceResponse.of(savedMemberRoutine);
	}

	private MemberDailyRoutine getMemberDailyRoutine(Member member, DailyRoutine routine) {
		checkDuplicatedMemberRoutine(member, routine);
		return completedMemberDailyRoutineRepository.findByMemberAndRoutine(member, routine)
				.map(completedRoutine -> recreateOldRoutines(member, routine, completedRoutine))
				.orElseGet(() -> createNewRoutine(member, routine));
	}

	private void checkDuplicatedMemberRoutine(Member member, DailyRoutine routine) {
		if (!member.isNotExistRoutine(routine)) {
			throw new RoutineException(DUPLICATED_ROUTINE);
		}
	}

	private MemberDailyRoutine createNewRoutine(Member member, DailyRoutine routine) {
		return memberDailyRoutineRepository.save(new MemberDailyRoutine(member, routine));
	}

	private MemberDailyRoutine recreateOldRoutines(
			Member member, DailyRoutine routine, CompletedMemberDailyRoutine completedRoutine) {
		val isTodayAchieved = isTodayAchieved(completedRoutine);
		completedMemberDailyRoutineRepository.delete(completedRoutine);
		val memberRoutine = new MemberDailyRoutine(member, routine, completedRoutine.getAchieveCount(), isTodayAchieved);
		return memberDailyRoutineRepository.save(memberRoutine);
	}

	private boolean isTodayAchieved(CompletedMemberDailyRoutine completedRoutine) {
		return completedRoutine.getIsAchieve() && isTodayCompleted(completedRoutine);
	}

	private boolean isTodayCompleted(CompletedMemberDailyRoutine completedRoutine) {
		val now = LocalDate.now();
		return completedRoutine.getCreatedAt().toLocalDate().equals(now);
	}

	@Override
	@Transactional
	public void createMemberDailyRoutines(Member member, List<Long> routines) {
		routines.forEach(routineId -> memberDailyRoutineRepository
				.save(new MemberDailyRoutine(member, findRoutine(routineId))));
	}

	private DailyRoutine findRoutine(long id) {
		return dailyRoutineRepository.findById(id)
			.orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
	}

	@Override
	@Transactional
	public void deleteMemberDailyRoutines(MemberDailyRoutineDeleteServiceRequest request) {
		val member = findMember(request.memberId());
		val routines = getMemberRoutines(member, request.routineIds());
		deleteMemberRoutines(routines);
	}

	private List<MemberDailyRoutine> getMemberRoutines(Member member, List<Long> routineIds) {
		return routineIds.stream()
				.map(this::findMemberRoutine)
				.filter(member::isDailyRoutineForMember)
				.toList();
	}

	private void deleteMemberRoutines(List<MemberDailyRoutine> routines) {
		routines.forEach(this::deleteMemberRoutine);
	}

	private void deleteMemberRoutine(MemberDailyRoutine routine) {
		moveCompletedRoutine(routine);
		memberDailyRoutineRepository.delete(routine);
	}

	private void moveCompletedRoutine(MemberDailyRoutine routine) {
		val completedRoutine = new CompletedMemberDailyRoutine(routine);
		completedMemberDailyRoutineRepository.save(completedRoutine);
	}

	@Override
	@Transactional
	public MemberDailyRoutineAchieveServiceResponse achieveMemberDailyRoutine(MemberDailyRoutineAchieveServiceRequest request) {
		val member = findMember(request.memberRoutineId());
		val routine = findMemberRoutine(request.memberRoutineId());
		member.checkDailyRoutineForMember(routine);
		routine.achieveRoutine();
		return MemberDailyRoutineAchieveServiceResponse.of(routine);
	}

	private MemberDailyRoutine findMemberRoutine(long id) {
		return memberDailyRoutineRepository.findById(id)
			.orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
	}

	@Override
	public MemberDailyRoutineListGetServiceResponse getMemberDailyRoutines(MemberDailyRoutineListGetServiceRequest request) {
		val member = findMember(request.memberId());
		val routines = memberDailyRoutineRepository.findAllByMember(member);
		return MemberDailyRoutineListGetServiceResponse.of(routines);
	}

	private Member findMember(long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new MemberException(INVALID_MEMBER));
	}

	@Override
	@Transactional
	public void initMemberDailyRoutines() {
		val routines = memberDailyRoutineRepository.findAllByAchieved();
		routines.forEach(MemberDailyRoutine::initAchievement);
	}

	@Override
	@Transactional
	public void deleteMemberDailyRoutine(MemberDailyRoutine routine) {
		memberDailyRoutineRepository.delete(routine);
	}
}
