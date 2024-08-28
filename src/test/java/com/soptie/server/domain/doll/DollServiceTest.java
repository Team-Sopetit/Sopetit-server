package com.soptie.server.domain.doll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.domain.member.MemberService;
import com.soptie.server.persistence.repository.DollRepository;

@ExtendWith(MockitoExtension.class)
class DollServiceTest {

	@InjectMocks
	private MemberService memberService;

	@Mock
	private DollRepository dollRepository;

	@ParameterizedTest
	@DisplayName("[성공] 인형 타입에 해당하는 인형 이미지 주소를 토대로 DollImageResponse를 생성한다.")
	@CsvSource(value = {"brown, BROWN", "gray, GRAY", "red, RED", "white, WHITE"})
	void acquireImageUriByDollType(String faceImageUrl, DollType dollType) { // TODO: 테스트
		// given

		// when

		// then
	}
}
