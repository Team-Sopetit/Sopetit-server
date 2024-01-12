package com.soptie.server.memberRoutine.service;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineResponse;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import com.soptie.server.memberRoutine.repository.MemberHappinessRoutineRepository;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;
import com.soptie.server.routine.repository.happiness.routine.HappinessSubRoutineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.soptie.server.member.message.ErrorMessage.INVALID_MEMBER;
import static com.soptie.server.routine.message.ErrorMessage.INVALID_ROUTINE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberHappinessRoutineServiceImpl implements MemberHappinessRoutineService {

    private final MemberHappinessRoutineRepository memberHappinessRoutineRepository;
    private final HappinessSubRoutineRepository happinessSubRoutineRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberHappinessRoutineResponse createMemberHappinessRoutine(long memberId, MemberHappinessRoutineRequest request) {
        val member = findMember(memberId);
        val routine = findRoutine(request.routineId());
        val memberRoutine = new MemberHappinessRoutine(member, routine);
        val savedMemberRoutine = memberHappinessRoutineRepository.save(memberRoutine);
        return MemberHappinessRoutineResponse.of(savedMemberRoutine);
    }

    private HappinessSubRoutine findRoutine(long id) {
        return happinessSubRoutineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_ROUTINE.getMessage()));
    }

    private Member findMember(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_MEMBER.getMeesage()));
    }
}
