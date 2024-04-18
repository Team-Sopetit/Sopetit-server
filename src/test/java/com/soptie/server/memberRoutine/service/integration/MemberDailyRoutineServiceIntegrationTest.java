package com.soptie.server.memberRoutine.service.integration;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.repository.MemberDailyRoutineRepository;
import com.soptie.server.memberRoutine.service.MemberDailyRoutineServiceImpl;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.MemberDailyRoutineFixture;
import com.soptie.server.support.fixture.MemberFixture;

@IntegrationTest
@Transactional
class MemberDailyRoutineServiceIntegrationTest {

	@Autowired
	MemberDailyRoutineServiceImpl memberDailyRoutineService;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberDailyRoutineRepository memberDailyRoutineRepository;

	@Nested
	class deleteMemberDailyRoutine {

		Member member;
		MemberDailyRoutine memberDailyRoutine;

		@BeforeEach
		void setUp() {
			member = memberRepository.save(MemberFixture.member().build());
			memberDailyRoutine = memberDailyRoutineRepository.save(MemberDailyRoutineFixture.memberRoutine().member(member).build());
		}

		@Test
		@DisplayName("[성공] 회원의 데일리 루틴이 유효하면 삭제된다.")
		void DeleteValidMemberDailyRoutine() {
			// given
			Long memberRoutineId = memberDailyRoutine.getId();

			// when
			memberDailyRoutineService.deleteMemberDailyRoutine(memberDailyRoutine);

			// then
			assertThat(memberDailyRoutineRepository.findById(memberRoutineId)).isEmpty();
		}

		//TODO: [성공] 회원의 데일리 루틴이 유효하면 삭제되고, 별도 테이블로 복사된다.
	}
}
