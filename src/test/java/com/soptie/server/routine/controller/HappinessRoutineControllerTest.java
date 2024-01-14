package com.soptie.server.routine.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.soptie.server.base.BaseControllerTest;
import com.soptie.server.common.dto.Response;
import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;
import com.soptie.server.routine.fixture.HappinessRoutineFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HappinessRoutineController.class)
class HappinessRoutineControllerTest extends BaseControllerTest {

    @MockBean
    HappinessRoutineController controller;

    private final String DEFAULT_URL = "/api/v1/routines/happiness";
    private final String TAG = "HAPPINESS ROUTINE";

    @Test
    @DisplayName("행복 루틴 테마 리스트 조회 성공")
    void success_getHappinessThemes() throws Exception {
        // given
        HappinessThemesResponse themes = HappinessRoutineFixture.createHappinessThemesResponseDTO();
        ResponseEntity<Response> response = ResponseEntity.ok(Response.success("테마 조회 성공", themes));

        // when
        when(controller.getHappinessThemes()).thenReturn(response);

        // then
        mockMvc
                .perform(
                        RestDocumentationRequestBuilders.get(DEFAULT_URL + "/themes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(
                        MockMvcRestDocumentation.document(
                                "get-happiness-themes-docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag(TAG)
                                                .description("행복 루틴 테마 리스트 조회")
                                                .requestFields()
                                                .responseFields(
                                                        fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
                                                        fieldWithPath("message").type(STRING).description("응답 메시지"),
                                                        fieldWithPath("data").type(OBJECT).description("응답 데이터"),
                                                        fieldWithPath("data.themes").type(ARRAY).description("테마 정보 리스트"),
                                                        fieldWithPath("data.themes[].themeId").type(NUMBER).description("테마 id"),
                                                        fieldWithPath("data.themes[].name").type(STRING).description("테마 이름")
                                                )
                                                .build())))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("테마 별 행복 루틴 리스트 조회 성공")
    void success_getHappinessRoutinesByTheme() throws Exception {
        HappinessRoutinesResponse routines = HappinessRoutineFixture.createHappinessRoutinesResponseDTO();
        ResponseEntity<Response> response = ResponseEntity.ok(Response.success("루틴 조회 성공", routines));

        when(controller.getHappinessRoutinesByThemes(anyLong())).thenReturn(response);

        mockMvc
                .perform(
                        RestDocumentationRequestBuilders.get(DEFAULT_URL + "/theme/{themeId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(
                        MockMvcRestDocumentation.document(
                                "get-theme-happiness-routines-docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag(TAG)
                                                .description("테마 별 행복 루틴 리스트 조회")
                                                .pathParameters(
                                                        parameterWithName("themeId").description("테마 id")
                                                )
                                                .requestFields()
                                                .responseFields(
                                                        fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
                                                        fieldWithPath("message").type(STRING).description("응답 메시지"),
                                                        fieldWithPath("data").type(OBJECT).description("응답 데이터"),
                                                        fieldWithPath("data.routines").type(ARRAY).description("루틴 정보 리스트"),
                                                        fieldWithPath("data.routines[].routineId").type(NUMBER).description("행복 루틴 id"),
                                                        fieldWithPath("data.routines[].name").type(STRING).description("행복 루틴 제목"),
                                                        fieldWithPath("data.routines[].nameColor").type(STRING).description("행복 루틴 제목 색상"),
                                                        fieldWithPath("data.routines[].title").type(STRING).description("행복 루틴 설명"),
                                                        fieldWithPath("data.routines[].iconImageUrl").type(STRING).description("이미지 url")
                                                )
                                                .build())))
                .andExpect(status().isOk());
    }
}