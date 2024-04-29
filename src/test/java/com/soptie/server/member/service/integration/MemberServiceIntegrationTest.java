package com.soptie.server.member.service.integration;

import static org.assertj.core.api.Assertions.*;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.repository.DollRepository;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.member.service.MemberServiceImpl;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;
import com.soptie.server.routine.repository.daily.routine.DailyRoutineRepository;
import com.soptie.server.routine.repository.daily.theme.DailyThemeRepository;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.DailyRoutineFixture;
import com.soptie.server.support.fixture.DailyThemeFixture;
import com.soptie.server.support.fixture.DollFixture;
import com.soptie.server.support.fixture.MemberFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.soptie.server.doll.entity.DollType.*;

@IntegrationTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DailyRoutineRepository dailyRoutineRepository;

    @Autowired
    private DailyThemeRepository dailyThemeRepository;

    @Autowired
    private DollRepository dollRepository;

    @Nested
    class createMember {

        Member member;
        DollType dollType = BROWN;
        DailyRoutine dailyRoutine;

        @BeforeEach
        void setUp() {
            member = memberRepository.save(MemberFixture.member().build());
            DailyTheme dailyTheme = dailyThemeRepository.save(DailyThemeFixture.dailyTheme().name("dailyTheme").build());
            dollRepository.save(DollFixture.doll().dollType(dollType).build());
            dailyRoutine = dailyRoutineRepository.save(DailyRoutineFixture.dailyRoutine().content("content").theme(dailyTheme).build());
        }

        @Test
        @DisplayName("[성공] 응답이 유효하면 회원 프로필이 생성된다.")
        void CreateMemberProfile() {
            // given
            String name = "doll";
            List<Long> routines = List.of(dailyRoutine.getId());
            MemberProfileRequest request = new MemberProfileRequest(dollType, name, routines);

            // when
            memberService.createMemberProfile(member.getId(), request);

            // then
            Member foundMember = memberRepository.findById(member.getId()).get();
            assertThat(foundMember.getMemberDoll().getDoll().getDollType()).isEqualTo(dollType);
            assertThat(foundMember.getMemberDoll().getName()).isEqualTo(name);
            assertThat(foundMember.getDailyRoutines().size()).isEqualTo(routines.size());
            assertThat(foundMember.getDailyRoutines().get(0).getId()).isEqualTo(dailyRoutine.getId());
        }
    }
}
