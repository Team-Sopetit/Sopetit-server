package com.soptie.server.doll.controller;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.soptie.server.base.BaseControllerTest;
import com.soptie.server.common.config.ValueConfig;
import com.soptie.server.common.dto.Response;
import com.soptie.server.doll.dto.DollImageResponse;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.fixture.DollFixture;

@WebMvcTest(DollController.class)
class DollControllerTest extends BaseControllerTest {

	@MockBean
	DollController controller;
	@MockBean
	ValueConfig valueConfig;

	private final String DEFAULT_URL = "/api/v1/dolls";
	private final String TAG = "DOLL";

	@Test
	@DisplayName("인형 이미지 조회 성공")
	void success_getDollImage() throws Exception {
		// given
		DollType dollType = DollType.BROWN;
		DollImageResponse dollImage = DollFixture.createDollImageResponseDTO();
		ResponseEntity<Response> response = ResponseEntity.ok(Response.success("인형 이미지 조회 성공", dollImage));

		// when
		when(controller.getDollImages(dollType)).thenReturn(response);

		// then
		mockMvc
			.perform(
				RestDocumentationRequestBuilders.get(DEFAULT_URL + "/image/{type}", dollType)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andDo(
				MockMvcRestDocumentation.document(
					"get-doll-image-docs",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					resource(
						ResourceSnippetParameters.builder()
							.tag(TAG)
							.description("인형 이미지 조회")
							.pathParameters(
								parameterWithName("type").description("인형 타입")
							)
							.requestFields()
							.responseFields(
								fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
								fieldWithPath("message").type(STRING).description("응답 메시지"),
								fieldWithPath("data").type(OBJECT).description("응답 데이터"),
								fieldWithPath("data.faceImageUrl").type(STRING).description("인형 얼굴 이미지 url")
							)
							.build())))
			.andExpect(status().isOk());
	}
}