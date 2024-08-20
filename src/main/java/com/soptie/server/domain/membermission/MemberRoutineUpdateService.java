package com.soptie.server.domain.membermission;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberRoutineUpdateService {/*
	private final MemberAdapter memberAdapter;

	private final MemberRoutineFinder memberRoutineFinder;
	private final MemberRoutineDeleter memberRoutineDeleter;

	//TODO: challenge
	public MemberRoutineAchieveServiceResponse updateAchievementStatus(MemberRoutineAchieveServiceRequest request) {
		val member = memberAdapter.findById(request.memberId());
		val memberRoutine = memberRoutineFinder.findById(request.memberRoutineId());
		val isAchievedToday = memberRoutine.isAchieveToday();

		memberRoutine.checkMemberHas(member);

		if (memberRoutine.isAchieve()) {
			memberRoutine.cancelAchievement();
		} else {
			if (!isAchievedToday) {
				if (memberRoutine.getType() == DAILY) {
					member.getCottonInfo().addBasicCottonCount();
				} else {
					member.getCottonInfo().addRainbowCottonCount();
				}
				memberAdapter.update(member);
			}
			memberRoutine.achieve();
			deleteMemberRoutineIfTypeIsOneTime(memberRoutine);
		}

		return MemberRoutineAchieveServiceResponse.of(memberRoutine, !isAchievedToday);
	}

	private void deleteMemberRoutineIfTypeIsOneTime(MemberRoutine memberRoutine) {
		if (memberRoutine.getType().equals(CHALLENGE)) {
			memberRoutineDeleter.softDelete(memberRoutine);
		}
	}
	*/
}
