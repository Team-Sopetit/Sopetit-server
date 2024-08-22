package com.soptie.server.domain.doll;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.soptie.server.domain.member.MemberService;
import com.soptie.server.persistence.repository.DollRepository;

@ExtendWith(MockitoExtension.class)
class DollServiceImplTest {

	@InjectMocks
	private MemberService memberService;

	@Mock
	private DollRepository dollRepository;

	// @ParameterizedTest
	// @DisplayName("인형 타입에 해당하는 인형 이미지 주소를 토대로 DollImageResponse를 생성한다.")
	// @CsvSource(value = {"brown, BROWN", "gray, GRAY", "red, RED", "white, WHITE"})
	// void acquireImageUriByDollType(String faceImageUrl, DollType dollType) {
	// 	// given
	// 	Long id = 1L;
	// 	Doll doll = doll(id, dollType, faceImageUrl);
	//
	// 	// when
	// 	DollImageResponse response = dollService.getDollImage(dollType);
	//
	// 	// then
	// 	assertThat(DollImageResponse.of(doll)).isEqualTo(response);
	// }

	// private Doll doll(Long id, DollType dollType, String faceImageUrl) {
	// 	Doll doll = DollFixture.doll().id(id).dollType(dollType).faceImageUrl(faceImageUrl).build();
	// 	doReturn(Optional.of(doll)).when(dollRepository).findByDollType(dollType);
	// 	return doll;
	// }
}
