package com.soptie.server.memberRoutine.service;

import static com.soptie.server.member.message.ErrorCode.*;
import static com.soptie.server.routine.message.RoutineErrorCode.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.memberRoutine.adapter.MemberRoutineDeleter;
import com.soptie.server.memberRoutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberRoutine.adapter.MemberRoutineSaver;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineAchieveServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineCreateServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponse;
import com.soptie.server.routine.adapter.RoutineFinder;
import com.soptie.server.routine.exception.RoutineException;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRoutineService {

	private final MemberRoutineSaver memberRoutineSaver;
	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberRoutineDeleter memberRoutineDeleter;
	private final MemberFinder memberFinder;
	private final RoutineFinder routineFinder;

	@Transactional
	public MemberDailyRoutineCreateServiceResponse createDailyRoutine(MemberDailyRoutineCreateServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val routine = routineFinder.findById(request.routineId());

		if (memberRoutineFinder.isExist(member, routine)) {
			throw new RoutineException(DUPLICATED_ROUTINE);
		}

		val savedMemberRoutine = memberRoutineSaver.checkHasDeletedAndSave(member, routine);
		return MemberDailyRoutineCreateServiceResponse.of(savedMemberRoutine);
	}

	@Transactional
	public void createDailyRoutines(Member member, List<Long> routineIds) {
		routineIds.forEach(id -> memberRoutineSaver.checkHasDeletedAndSave(member, routineFinder.findById(id)));
	}

	@Transactional
	public void deleteDailyRoutines(MemberDailyRoutineDeleteServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val routines = filterMemberRoutines(member, request.routineIds());
		routines.forEach(memberRoutineDeleter::softDelete);
	}

	@Transactional
	public MemberDailyRoutineAchieveServiceResponse achieveDailyRoutine(MemberDailyRoutineAchieveServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findById(request.memberRoutineId());

		if (!memberRoutine.getMember().equals(member)) {
			throw new MemberException(INACCESSIBLE_ROUTINE);
		}

		memberRoutine.achieve();
		member.addDailyCotton();

		return MemberDailyRoutineAchieveServiceResponse.of(memberRoutine);
	}

	public MemberDailyRoutineListGetServiceResponse getDailyRoutines(MemberDailyRoutineListGetServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val routines = memberRoutineFinder.findDailyRoutinesByMember(member);
		return MemberDailyRoutineListGetServiceResponse.of(routines);
	}

	@Transactional
	public void initDailyRoutines() { //TODO: 스케줄러 테스트 필요
		val routines = memberRoutineFinder.findAchieved();
		routines.forEach(MemberRoutine::initAchieve);
	}

	private List<MemberRoutine> filterMemberRoutines(Member member, List<Long> routineIds) {
		return routineIds.stream()
				.map(memberRoutineFinder::findById)
				.filter(routine -> routine.getMember().equals(member))
				.toList();
	}
}
