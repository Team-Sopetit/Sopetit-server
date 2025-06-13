package com.soptie.server.batch;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.memberroutine.MemberRoutineService;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.converter.MemberConverter;
import com.soptie.server.persistence.entity.MemberEntity;
import com.soptie.server.persistence.entity.routine.MemberRoutineEntity;
import com.soptie.server.persistence.entity.routine.RoutineEntity;
import com.soptie.server.persistence.repository.MemberRepository;
import com.soptie.server.persistence.repository.routine.MemberRoutineRepository;
import com.soptie.server.persistence.repository.routine.RoutineRepository;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.MemberFixture;
import com.soptie.server.support.fixture.MemberRoutineFixture;
import com.soptie.server.support.fixture.RoutineFixture;

@IntegrationTest
public class MemberRoutineSchedulerTest implements SchedulingConfigurer {

	@Autowired
	private MemberRoutineScheduler memberRoutineScheduler;

	@Autowired
	private BatchProperties batchProperties;

	@SpyBean
	private MemberRoutineService memberRoutineService;

	@SpyBean
	private MemberRoutineRepository memberRoutineRepository;

	@SpyBean
	private MemberRepository memberRepository;

	@SpyBean
	private RoutineRepository routineRepository;

	private MemberEntity savedMember;
	private List<RoutineEntity> savedRoutines;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		// 테스트에서 cron 트리거를 위한 스케줄링 등록
		String cronExpression = batchProperties.cron().init().routine();
		taskRegistrar.addCronTask(() -> memberRoutineScheduler.initMemberDailyRoutines(), cronExpression);
	}

	@BeforeEach
	public void setUp() {
		savedMember = memberRepository.save(new MemberEntity(MemberFixture.createDefault()));
		savedRoutines = routineRepository.saveAll(
			IntStream.range(0, 5)
				.mapToObj(i -> new RoutineEntity(RoutineFixture.create().themeId((long)i).build()))
				.toList());
	}

	@Test
	@Transactional
	@DisplayName(value = "전체 회원 루틴의 하루 달성 정보를 초기화한다.")
	public void testInitMemberRoutineAchievement() {
		// given
		List<MemberRoutineEntity> memberRoutines = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			MemberRoutine achievedMemberRoutine = createAchievedMemberRoutine(i);
			memberRoutines.add(new MemberRoutineEntity(achievedMemberRoutine));
		}
		memberRoutineRepository.saveAll(memberRoutines);

		// when
		memberRoutineScheduler.initMemberDailyRoutines();

		// then
		verify(memberRoutineService, times(1)).initAchievement();

		long count = memberRoutineRepository.countByAchieved(true);
		assertEquals(0, count);
	}

	private MemberRoutine createAchievedMemberRoutine(int themeIdx) {
		Member member = MemberConverter.convert(savedMember);
		Routine routine = savedRoutines.get(themeIdx).toDomain();
		MemberRoutine memberRoutine = MemberRoutineFixture.createDefault(member, routine);
		memberRoutine.achieve(); // 루틴 달성
		return memberRoutine;
	}
}
