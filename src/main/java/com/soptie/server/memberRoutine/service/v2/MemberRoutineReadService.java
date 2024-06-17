package com.soptie.server.memberRoutine.service.v2;

import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.memberRoutine.adapter.MemberRoutineFinder;
import com.soptie.server.memberRoutine.service.v2.dto.request.MemberDailyRoutineListGetServiceRequest;
import com.soptie.server.memberRoutine.service.v2.dto.response.MemberDailyRoutineListGetServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRoutineReadService {

    private final MemberRoutineFinder memberRoutineFinder;
    private final MemberFinder memberFinder;

    public MemberDailyRoutineListGetServiceResponse getDailyRoutines(MemberDailyRoutineListGetServiceRequest request) {
        val member = memberFinder.findById(request.memberId());
        val routines = memberRoutineFinder.findDailyRoutinesByMember(member);
        return MemberDailyRoutineListGetServiceResponse.of(routines);
    }
}
