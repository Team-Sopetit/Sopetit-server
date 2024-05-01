package com.soptie.server.memberRoutine.service.daily;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineAchieveServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineCreateServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineDeleteServiceRequest;
import com.soptie.server.memberRoutine.service.dto.request.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineAchieveServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineCreateServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponse;

import java.util.List;

public interface MemberDailyRoutineService {

	MemberDailyRoutineCreateServiceResponse createMemberDailyRoutine(MemberDailyRoutineCreateServiceRequest request);
	void createMemberDailyRoutines(Member member, List<Long> routines);
	void deleteMemberDailyRoutines(MemberDailyRoutineDeleteServiceRequest request);
	MemberDailyRoutineAchieveServiceResponse achieveMemberDailyRoutine(MemberDailyRoutineAchieveServiceRequest request);
	MemberDailyRoutineListGetServiceResponse getMemberDailyRoutines(MemberDailyRoutineListGetServiceRequest request);
	void initMemberDailyRoutines();
	void deleteMemberDailyRoutine(MemberDailyRoutine routine);
}
