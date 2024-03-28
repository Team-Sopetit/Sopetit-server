package com.soptie.server.memberRoutine.service;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineResponse;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import com.soptie.server.memberRoutine.repository.MemberHappinessRoutineRepository;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;
import com.soptie.server.routine.repository.happiness.routine.HappinessRoutineRepository;
import com.soptie.server.routine.repository.happiness.routine.HappinessSubRoutineRepository;
import com.soptie.server.support.MemberFixture;
import com.soptie.server.support.MemberHappinessRoutineFixture;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@ExtendWith(MockitoExtension.class)
public class MemberHappinessRoutineServiceImplTest {
    @InjectMocks
    private MemberHappinessRoutineServiceImpl memberHappinessRoutineService;

    @Mock
    private MemberHappinessRoutineRepository memberHappinessRoutineRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private HappinessRoutineRepository happinessRoutineRepository;

    @Mock
    private HappinessSubRoutineRepository happinessSubRoutineRepository;


    @Test
    void 회원이_행복루틴을_추가하면_회원의_행복루틴이_생성된다() {
        // given
        long memberId = 1L;
        Member member = member(memberId);

        long routineId = 2L;
        HappinessSubRoutine happinessSubRoutine = new HappinessSubRoutine();

        MemberHappinessRoutine memberHappinessRoutine = MemberHappinessRoutineFixture.memberHappinessRoutine()
                .id(3L).member(member).routine(happinessSubRoutine).build();

        doReturn(memberHappinessRoutine).when(memberHappinessRoutineRepository).save(any(MemberHappinessRoutine.class));
        doReturn(Optional.of(happinessSubRoutine)).when(happinessSubRoutineRepository).findById(anyLong());

        // when
        final MemberHappinessRoutineRequest request = new MemberHappinessRoutineRequest(routineId);
        final MemberHappinessRoutineResponse actual = memberHappinessRoutineService.createMemberHappinessRoutine(memberId, request);

        // then
        assertThat(actual.routineId()).isEqualTo(memberHappinessRoutine.getId());
    }

    private Member member(long memberId) {
        Member member = MemberFixture.member().id(memberId).build();
        doReturn(Optional.of(member)).when(memberRepository).findById(memberId);
        return member;
    }

}
