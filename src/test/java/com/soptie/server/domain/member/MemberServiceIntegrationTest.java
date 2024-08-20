package com.soptie.server.domain.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.support.IntegrationTest;

@IntegrationTest
@Transactional
public class MemberServiceIntegrationTest {

	@Autowired
	private MemberService memberService;

	/*
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
			CreateProfileRequest controllerRequest = new CreateProfileRequest(dollType, name, routines);
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
	*/
}
