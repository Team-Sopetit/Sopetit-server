package com.soptie.server.domain.member;

import static com.soptie.server.domain.doll.DollType.*;
import static com.soptie.server.persistence.entity.deleted.RoutineType.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.request.member.MemberProfileCreateRequest;
import com.soptie.server.domain.doll.DollType;
import com.soptie.server.persistence.entity.deleted.Theme;
import com.soptie.server.persistence.repository.DollRepository;
import com.soptie.server.persistence.repository.MemberRepository;
import com.soptie.server.persistence.repository.RoutineRepository;
import com.soptie.server.persistence.repository.ThemeRepository;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.DollFixture;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.fixture.RoutineFixture;
import com.soptie.server.support.fixture.ThemeFixture;

@IntegrationTest
@Transactional
public class MemberServiceIntegrationTest {

	@Autowired
	private MemberService memberService;

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

	@SuppressWarnings("checkstyle:TypeName")
	@Nested
	class Create {

		Member member;
		DollType dollType = BROWN;
		Routine routine;

		@BeforeEach
		void setUp() {
			member = memberRepository.save(MemberFixture.member().build());
			Theme theme = themeRepository.save(ThemeFixture.theme().name("theme").build());
			dollRepository.save(DollFixture.doll().dollType(dollType).build());
			routine = routineRepository.save(
				RoutineFixture.routine().content("content").type(DAILY).theme(theme).build());
		}

		@Test
		@DisplayName("[성공] 응답이 유효하면 회원 프로필이 생성된다.")
		void registerProfile() {
			// given
			String name = "doll";
			List<Long> routines = List.of(routine.getId());
			MemberProfileCreateRequest controllerRequest = new MemberProfileCreateRequest(dollType, name, routines);
			MemberProfileCreateServiceRequest request = MemberProfileCreateServiceRequest.of(member.getId(),
				controllerRequest);

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