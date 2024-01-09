package com.soptie.server.routine.controller;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
import com.soptie.server.common.dto.Response;
import com.soptie.server.routine.dto.DailyThemesResponse;
import com.soptie.server.routine.fixture.DailyRoutineFixture;

@WebMvcTest(DailyRoutineController.class)
class DailyRoutineControllerTest extends BaseControllerTest {

	@MockBean
	DailyRoutineController controller;

	private final String DEFAULT_URL = "/api/v1/routines/daily";
	private final String TAG = "DAILY ROUTINE";

	@Test
	@DisplayName("데일리 루틴 테마 리스트 조회 성공")
	void success_getDailyThemes() throws Exception {
		// given
		DailyThemesResponse themes = DailyRoutineFixture.createDailyThemesResponseDTO();
		ResponseEntity<Response> response = ResponseEntity.ok(Response.success("테마 전체 조회", themes));

		// when
		when(controller.getThemes()).thenReturn(response);

		// then
		mockMvc
			.perform(
				RestDocumentationRequestBuilders.get(DEFAULT_URL + "/themes")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andDo(
				MockMvcRestDocumentation.document(
					"get-themes-docs",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					resource(
						ResourceSnippetParameters.builder()
							.tag(TAG)
							.description("데일리 루틴 테마 리스트 조회")
							.requestFields()
							.responseFields(
								fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
								fieldWithPath("message").type(STRING).description("응답 메시지"),
								fieldWithPath("data").type(OBJECT).description("응답 데이터"),
								fieldWithPath("data.themes").type(ARRAY).description("테마 정보 리스트"),
								fieldWithPath("data.themes[].themeId").type(NUMBER).description("테마 id"),
								fieldWithPath("data.themes[].name").type(STRING).description("테마 이름"),
								fieldWithPath("data.themes[].iconImageUrl").type(STRING).description("아이콘 이미지 url"),
								fieldWithPath("data.themes[].backgroundImageUrl").type(STRING).description("배경 이미지 url")
							)
							.build())))
			.andExpect(status().isOk());
	}

}