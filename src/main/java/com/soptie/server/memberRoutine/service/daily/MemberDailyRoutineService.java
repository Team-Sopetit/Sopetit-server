package com.soptie.server.memberRoutine.service.daily;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutinesResponse;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.service.daily.dto.request.MemberDailyRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.daily.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.daily.dto.request.MemberDailyRoutineDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.daily.dto.response.MemberDailyRoutineAchieveServiceResponse;
import com.soptie.server.memberRoutine.service.daily.dto.response.MemberDailyRoutineCreateServiceResponse;

import java.util.List;

public interface MemberDailyRoutineService {

	MemberDailyRoutineCreateServiceResponse createMemberDailyRoutine(MemberDailyRoutineCreateServiceRequest request);
	void createMemberDailyRoutines(Member member, List<Long> routines);
	void deleteMemberDailyRoutines(MemberDailyRoutineDeleteServiceRequest request);
	MemberDailyRoutineAchieveServiceResponse achieveMemberDailyRoutine(MemberDailyRoutineAchieveServiceRequest request);
	MemberDailyRoutinesResponse getMemberDailyRoutines(long memberId);
	void initMemberDailyRoutines();
	void deleteMemberDailyRoutine(MemberDailyRoutine routine);
}
