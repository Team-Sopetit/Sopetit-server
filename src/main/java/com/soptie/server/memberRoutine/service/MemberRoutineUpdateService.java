package com.soptie.server.memberRoutine.service;

import static com.soptie.server.routine.entity.RoutineType.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.history.achievement.adapter.HistoryAchievedFinder;
import com.soptie.server.history.achievement.adapter.HistoryAchievedSaver;
import com.soptie.server.history.achievement.entity.HistoryAchieved;
import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.memberRoutine.adapter.MemberRoutineDeleter;
import com.soptie.server.memberRoutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.memberRoutine.service.dto.request.MemberRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.dto.response.MemberRoutineAchieveServiceResponse;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberRoutineUpdateService {

	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberRoutineDeleter memberRoutineDeleter;
	private final MemberFinder memberFinder;
	private final HistoryAchievedSaver achievedSaver;
	private final HistoryAchievedFinder achievedFinder;

	public MemberRoutineAchieveServiceResponse achieveMemberRoutine(MemberRoutineAchieveServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findById(request.memberRoutineId());
		memberRoutine.checkMemberHas(member);

		updateAchieveState(memberRoutine);

		val isAchievedToday = achievedFinder.isAchievedToday(memberRoutine);
		if (!isAchievedToday) {
			member.addCottonCount(memberRoutine.getType());
		}

		achievedSaver.save(new HistoryAchieved(memberRoutine));

		return MemberRoutineAchieveServiceResponse.of(memberRoutine, isAchievedToday);
	}

	public void initDailyRoutines() {
		val routines = memberRoutineFinder.findAchieved();
		routines.forEach(MemberRoutine::initAchieve);
	}

	private void updateAchieveState(MemberRoutine memberRoutine) {
		if (memberRoutine.isAchieve()) {
			memberRoutine.cancel();
		} else {
			memberRoutine.achieve();
			deleteMemberRoutineIfTypeIsOneTime(memberRoutine);
		}
	}

	private void deleteMemberRoutineIfTypeIsOneTime(MemberRoutine memberRoutine) {
		if (memberRoutine.getType().equals(CHALLENGE)) {
			memberRoutineDeleter.softDelete(memberRoutine);
		}
	}
}
