package com.soptie.server.member.service.integration;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.repository.DollRepository;
import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.controller.dto.request.MemberProfileCreateRequest;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.member.service.MemberServiceImpl;
import com.soptie.server.member.service.dto.request.MemberProfileCreateServiceRequest;
import com.soptie.server.memberRoutine.adapter.MemberRoutineFinder;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.repository.RoutineRepository;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.DollFixture;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.fixture.RoutineFixture;
import com.soptie.server.support.fixture.ThemeFixture;
import com.soptie.server.theme.entity.Theme;
import com.soptie.server.theme.repository.ThemeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.soptie.server.doll.entity.DollType.BROWN;
import static com.soptie.server.routine.entity.RoutineType.DAILY;
import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private MemberFinder memberFinder;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private DollRepository dollRepository;

    @Autowired
    private MemberRoutineFinder memberRoutineFinder;

    @Nested
    class createMember {

        Member member;
        DollType dollType = BROWN;
        Routine routine;

        @BeforeEach
        void setUp() {
            member = memberRepository.save(MemberFixture.member().build());
            Theme theme = themeRepository.save(ThemeFixture.theme().name("theme").build());
            dollRepository.save(DollFixture.doll().dollType(dollType).build());
            routine = routineRepository.save(RoutineFixture.routine().content("content").type(DAILY).theme(theme).build());
        }

        @Test
        @DisplayName("[성공] 응답이 유효하면 회원 프로필이 생성된다.")
        void CreateMemberProfile() {
            // given
            String name = "doll";
            List<Long> routines = List.of(routine.getId());
            MemberProfileCreateRequest controllerRequest = new MemberProfileCreateRequest(dollType, name, routines);
            MemberProfileCreateServiceRequest request =
                    MemberProfileCreateServiceRequest.of(member.getId(), controllerRequest);

            // when
            memberService.createMemberProfile(request);

            // then
            Member foundMember = memberFinder.findById(member.getId());
            assertThat(foundMember.getMemberDoll().getDoll().getDollType()).isEqualTo(dollType);
            assertThat(foundMember.getMemberDoll().getName()).isEqualTo(name);
            assertThat(memberRoutineFinder.findAllByMember(foundMember).size()).isEqualTo(routines.size());
            assertThat(memberRoutineFinder.findAllByMember(foundMember).get(0).id()).isEqualTo(routine.getId());
        }
    }
}
