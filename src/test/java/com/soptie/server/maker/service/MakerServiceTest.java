package com.soptie.server.maker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.maker.adapter.MakerFinder;
import com.soptie.server.maker.entity.Maker;
import com.soptie.server.maker.service.dto.MakerListAcquireServiceResponse;
import com.soptie.server.support.fixture.MakerFixture;

@ExtendWith(MockitoExtension.class)
class MakerServiceTest {

	@InjectMocks
	private MakerService makerService;

	@Mock
	private MakerFinder makerFinder;

	@Test
	@DisplayName("[성공] 모든 메이커 테마를 조회한다.")
	void acquireAllTheme() {
		// given
		long makerId1 = 1L;
		long makerId2 = 2L;
		long themeId1 = 1L;
		long themeId2 = 2L;
		String name = "maker";
		String job = "job";
		String profileImageUrl = "profileImageUrl";
		String description = "description";
		String content = "content";
		List<String> tags = List.of("#tag1", "#tag2", "#tag3");
		Maker maker1 = MakerFixture.maker()
			.id(makerId1)
			.name(name)
			.job(job)
			.profileImageUrl(profileImageUrl)
			.description(description)
			.content(content)
			.themeId(themeId1)
			.tags(tags)
			.build();

		Maker maker2 = MakerFixture.maker()
			.id(makerId2)
			.name(name)
			.job(job)
			.profileImageUrl(profileImageUrl)
			.description(description)
			.content(content)
			.themeId(themeId2)
			.tags(tags)
			.build();

		doReturn(List.of(maker1, maker2)).when(makerFinder).findAll();

		// when
		MakerListAcquireServiceResponse result = makerService.acquireAll();

		// then
		assertThat(result.makers().size()).isEqualTo(2);
		assertThat(result.makers().get(0).makerId()).isEqualTo(makerId1);
		assertThat(result.makers().get(1).makerId()).isEqualTo(makerId2);
	}
}
