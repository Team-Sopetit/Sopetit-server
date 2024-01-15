package com.soptie.server.memberRoutine.service;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.repository.CompletedMemberDailyRoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompletedMemberDailyRoutineServiceImpl implements CompletedMemberDailyRoutineService {

    private final CompletedMemberDailyRoutineRepository completedMemberDailyRoutineRepository;

    @Override
    @Transactional
    public void deleteCompletedMemberDailyRoutines(Member member) {
        completedMemberDailyRoutineRepository.deleteAllByMember(member);
    }
}
