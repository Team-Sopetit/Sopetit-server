package com.soptie.server.memberRoutine.service;

import static com.soptie.server.member.message.ErrorMessage.*;
import static com.soptie.server.routine.message.ErrorMessage.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.repository.MemberDailyRoutineRepository;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDailyRoutineServiceImpl implements MemberDailyRoutineService {

	private final MemberDailyRoutineRepository memberDailyRoutineRepository;
	private final MemberRepository memberRepository;
	private final DailyRoutineRepository dailyRoutineRepository;

	@Override
	@Transactional
	public MemberDailyRoutineResponse createMemberDailyRoutine(long memberId, MemberDailyRoutineRequest request) {
		val member = findMember(memberId);
		val routine = findRoutine(request.routineId());
		val memberRoutine = new MemberDailyRoutine(member, routine);
		val savedMemberRoutine = memberDailyRoutineRepository.save(memberRoutine);
		return MemberDailyRoutineResponse.of(savedMemberRoutine.getId());
	}

	private Member findMember(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(INVALID_MEMBER.getMeesage()));
	}

	private DailyRoutine findRoutine(Long id) {
		return dailyRoutineRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(INVALID_ROUTINE.getMessage()));
	}
}
