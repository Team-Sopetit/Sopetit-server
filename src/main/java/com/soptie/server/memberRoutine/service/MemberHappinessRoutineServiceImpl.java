package com.soptie.server.memberRoutine.service;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutinesResponse;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import com.soptie.server.memberRoutine.repository.MemberHappinessRoutineRepository;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;
import com.soptie.server.routine.exception.RoutineException;
import com.soptie.server.routine.repository.happiness.routine.HappinessSubRoutineRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

import static com.soptie.server.member.message.ErrorCode.*;
import static com.soptie.server.routine.message.ErrorCode.*;

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
        member.checkHappinessRoutineAddition();
        val routine = findRoutine(request.subRoutineId());
        val memberRoutine = new MemberHappinessRoutine(member, routine);
        val savedMemberRoutine = memberHappinessRoutineRepository.save(memberRoutine);
        return MemberHappinessRoutineResponse.of(savedMemberRoutine);
    }

    private HappinessSubRoutine findRoutine(long id) {
        return happinessSubRoutineRepository.findById(id)
                .orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
    }

    @Override
    public Optional<MemberHappinessRoutinesResponse> getMemberHappinessRoutine(long memberId) {
        val member = findMember(memberId);
        val memberRoutine = member.getHappinessRoutine();
        if (Objects.isNull(memberRoutine)) {
            return Optional.empty();
        }
        val response = MemberHappinessRoutinesResponse.of(memberRoutine);
        return Optional.of(response);
    }

    @Override
    @Transactional
    public void deleteMemberHappinessRoutine(long memberId, long routineId) {
        val member = findMember(memberId);
        val memberRoutine = findMemberRoutine(routineId);
        member.checkHappinessRoutineForMember(memberRoutine);
        deleteMemberRoutine(memberRoutine);
    }

    private Member findMember(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private void deleteMemberRoutine(MemberHappinessRoutine routine) {
        routine.getMember().resetHappinessRoutine();
        memberHappinessRoutineRepository.delete(routine);
    }

    @Override
    @Transactional
    public void achieveMemberHappinessRoutine(long memberId, long routineId) {
        val member = findMember(memberId);
        val memberRoutine = findMemberRoutine(routineId);
        member.checkHappinessRoutineForMember(memberRoutine);
        member.addHappinessCotton();
        deleteMemberRoutine(memberRoutine);
    }

    private MemberHappinessRoutine findMemberRoutine(long id) {
        return memberHappinessRoutineRepository.findById(id)
                .orElseThrow(() -> new RoutineException(INVALID_ROUTINE));
    }

    @Override
    @Transactional
    public void deleteMemberHappinessRoutine(MemberHappinessRoutine routine) {
        memberHappinessRoutineRepository.delete(routine);
    }
}
