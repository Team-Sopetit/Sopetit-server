package com.soptie.server.memberRoutine.repository;

import java.util.List;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

public interface MemberDailyRoutineCustomRepository {
	List<MemberDailyRoutine> findAllByMember(Member member);
}
