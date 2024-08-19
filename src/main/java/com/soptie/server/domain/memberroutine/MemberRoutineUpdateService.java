package com.soptie.server.domain.memberroutine;

import static com.soptie.server.persistence.entity.RoutineType.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.adapter.MemberFinder;
import com.soptie.server.persistence.adapter.MemberRoutineDeleter;
import com.soptie.server.persistence.adapter.MemberRoutineFinder;
import com.soptie.server.persistence.entity.MemberRoutine;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberRoutineUpdateService {

	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberRoutineDeleter memberRoutineDeleter;
	private final MemberFinder memberFinder;

	public MemberRoutineAchieveServiceResponse updateAchievementStatus(MemberRoutineAchieveServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findById(request.memberRoutineId());
		val isAchievedToday = memberRoutine.isAchieveToday();

		memberRoutine.checkMemberHas(member);

		if (memberRoutine.isAchieve()) {
			memberRoutine.cancelAchievement();
		} else {
			if (!isAchievedToday) {
				member.addCottonCount(memberRoutine.getType());
			}
			memberRoutine.achieve();
			deleteMemberRoutineIfTypeIsOneTime(memberRoutine);
		}

		return MemberRoutineAchieveServiceResponse.of(memberRoutine, !isAchievedToday);
	}

	public void initDailyRoutines() {
		val routines = memberRoutineFinder.findAchieved();
		routines.forEach(MemberRoutine::initAchieve);
	}

	private void deleteMemberRoutineIfTypeIsOneTime(MemberRoutine memberRoutine) {
		if (memberRoutine.getType().equals(CHALLENGE)) {
			memberRoutineDeleter.softDelete(memberRoutine);
		}
	}
}
