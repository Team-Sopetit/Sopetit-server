package com.soptie.server.domain.doll;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.api.controller.dto.response.doll.DollImageResponse;
import com.soptie.server.persistence.entity.Doll;
import com.soptie.server.persistence.entity.DollType;
import com.soptie.server.persistence.repository.DollRepository;
import com.soptie.server.support.fixture.DollFixture;

@ExtendWith(MockitoExtension.class)
class DollServiceImplTest {

	@InjectMocks
	private DollServiceImpl dollService;

	@Mock
	private DollRepository dollRepository;

	@ParameterizedTest
	@DisplayName("인형 타입에 해당하는 인형 이미지 주소를 토대로 DollImageResponse를 생성한다.")
	@CsvSource(value = {"brown, BROWN", "gray, GRAY", "red, RED", "white, WHITE"})
	void acquireImageUriByDollType(String faceImageUrl, DollType dollType) {
		// given
		Long id = 1L;
		Doll doll = doll(id, dollType, faceImageUrl);

		// when
		DollImageResponse response = dollService.getDollImage(dollType);

		// then
		assertThat(DollImageResponse.of(doll)).isEqualTo(response);
	}

	private Doll doll(Long id, DollType dollType, String faceImageUrl) {
		Doll doll = DollFixture.doll().id(id).dollType(dollType).faceImageUrl(faceImageUrl).build();
		doReturn(Optional.of(doll)).when(dollRepository).findByDollType(dollType);
		return doll;
	}
}
