package com.soptie.server.maker.service.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.persistence.entity.Maker;
import com.soptie.server.persistence.repository.MakerRepository;
import com.soptie.server.domain.maker.MakerService;
import com.soptie.server.domain.maker.MakerListAcquireServiceResponse;
import com.soptie.server.support.IntegrationTest;
import com.soptie.server.support.fixture.MakerFixture;
import com.soptie.server.support.fixture.ThemeFixture;
import com.soptie.server.persistence.entity.Theme;
import com.soptie.server.persistence.repository.ThemeRepository;

@IntegrationTest
@Transactional
public class MakerServiceIntegrationTest {

	@Autowired
	MakerService makerService;

	@Autowired
	MakerRepository makerRepository;

	@Autowired
	ThemeRepository themeRepository;

	@Nested
	class Acquire {

		Maker maker1;
		Maker maker2;
		Theme theme1;
		Theme theme2;
		long makerId1 = 1L;
		long makerId2 = 2L;
		long themeId1 = 1L;
		long themeId2 = 2L;
		String name = "maker";
		String job = "job";
		String profileImageUrl = "profileImageUrl";
		String content = "content";
		List<String> tags = List.of("#tag1", "#tag2", "#tag3");

		@BeforeEach
		void setUp() {
			maker1 = makerRepository.save(MakerFixture.maker()
				.id(makerId1)
				.name(name)
				.job(job)
				.profileImageUrl(profileImageUrl)
				.content(content)
				.themeId(themeId1)
				.tags(tags)
				.build()
			);
			maker2 = makerRepository.save(MakerFixture.maker()
				.id(makerId2)
				.name(name)
				.job(job)
				.profileImageUrl(profileImageUrl)
				.content(content)
				.themeId(themeId2)
				.tags(tags)
				.build()
			);

			theme1 = themeRepository.save(ThemeFixture.theme().id(themeId1).name("테마 1").build());
			theme2 = themeRepository.save(ThemeFixture.theme().id(themeId2).name("테마 2").build());
		}

		@Test
		@DisplayName("[성공] 메이커와 해당하는 테마 정보를 조회한다.")
		void acquireAll() {
			// given, when
			MakerListAcquireServiceResponse actual = makerService.acquireAll();

			// then
			assertThat(actual.makers()).hasSize(2);
			assertThat(actual.makers().stream().filter(maker -> maker.makerId() == makerId1).findAny()).isNotEmpty();
			assertThat(actual.makers().stream().filter(maker -> maker.makerId() == makerId2).findAny()).isNotEmpty();
		}
	}
}
