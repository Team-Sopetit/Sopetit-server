package com.soptie.server.memberRoutine.service;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.dto.AchievedMemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutinesResponse;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

import java.util.List;

public interface MemberDailyRoutineService {
	MemberDailyRoutineResponse createMemberDailyRoutine(long memberId, MemberDailyRoutineRequest request);
	void createMemberDailyRoutines(Member member, List<Long> routines);
	void deleteMemberDailyRoutines(long memberId, List<Long> routineIds);
	AchievedMemberDailyRoutineResponse achieveMemberDailyRoutine(long memberId, Long routineId);
	MemberDailyRoutinesResponse getMemberDailyRoutines(long memberId);
	void initMemberDailyRoutines();
	void deleteMemberDailyRoutine(MemberDailyRoutine routine);
}
