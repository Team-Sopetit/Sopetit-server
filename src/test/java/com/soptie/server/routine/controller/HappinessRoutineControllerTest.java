package com.soptie.server.routine.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.soptie.server.base.BaseControllerTest;
import com.soptie.server.common.dto.Response;
import com.soptie.server.routine.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.dto.HappinessSubRoutinesResponse;
import com.soptie.server.routine.dto.HappinessThemesResponse;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;
import com.soptie.server.routine.entity.happiness.HappinessTheme;
import com.soptie.server.routine.entity.happiness.RoutineImage;
import com.soptie.server.routine.fixture.HappinessRoutineFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.soptie.server.routine.message.SuccessMessage.SUCCESS_GET_HAPPINESS_SUB_ROUTINES;
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

        MultiValueMap<String, String> queries = new LinkedMultiValueMap<>();
        String themes = "1";
        queries.add("themeId", themes);

        when(controller.getHappinessRoutinesByThemes(anyLong())).thenReturn(response);

        mockMvc
                .perform(
                        RestDocumentationRequestBuilders.get(DEFAULT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .params(queries))
                .andDo(
                        MockMvcRestDocumentation.document(
                                "get-theme-happiness-routines-docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag(TAG)
                                                .description("테마 별 행복 루틴 리스트 조회")
                                                .queryParameters(
                                                        parameterWithName("themeId").description("조회할 테마 id")
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

    @Test
    @DisplayName("행복 루틴 별 서브 행복 루틴 리스트 조회 성공")
    void success_getHappinessSubRoutinesByRoutineOfTheme()  throws Exception {
        // given
        RoutineImage routineImage = new RoutineImage("iconImageUrl", "contentImageUrl");
        HappinessTheme happinessTheme = new HappinessTheme(1L, "테마", "색깔", routineImage);
        HappinessRoutine happinessRoutine = new HappinessRoutine(1L, "타이틀", happinessTheme);
        HappinessSubRoutine happinessSubRoutine = new HappinessSubRoutine(1L, "content", "detailContent", "10분", "소프티숙소", happinessRoutine);
        List<HappinessSubRoutine> subRoutines = List.of(happinessSubRoutine);
        HappinessSubRoutinesResponse response = HappinessSubRoutinesResponse.of(subRoutines);
        ResponseEntity<Response> result = ResponseEntity.ok(Response.success(SUCCESS_GET_HAPPINESS_SUB_ROUTINES.getMessage(), response));

        // when
        when(controller.getHappinessSubRoutinesByRoutineOfTheme(anyLong())).thenReturn(result);

        // then
        mockMvc
                .perform(
                        RestDocumentationRequestBuilders.get(DEFAULT_URL + "/routine/{routineId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(
                        MockMvcRestDocumentation.document(
                                "get-sub-happiness-routines-by-routine-of-theme-docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag(TAG)
                                                .description("행복 루틴 별 서브 행복 루틴 리스트 조회")
                                                .pathParameters(
                                                        parameterWithName("routineId").description("루틴 id")
                                                )
                                                .requestFields()
                                                .responseFields(
                                                        fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
                                                        fieldWithPath("message").type(STRING).description("응답 메시지"),
                                                        fieldWithPath("data").type(OBJECT).description("응답 데이터"),
                                                        fieldWithPath("data.title").type(STRING).description("행복 루틴 주제"),
                                                        fieldWithPath("data.name").type(STRING).description("테마 이름"),
                                                        fieldWithPath("data.nameColor").type(STRING).description("색깔"),
                                                        fieldWithPath("data.iconImageUrl").type(STRING).description("아이콘 이미지 url"),
                                                        fieldWithPath("data.contentImageUrl").type(STRING).description("카드 이미지 url"),
                                                        fieldWithPath("data.subRoutines").type(ARRAY).description("서브 루틴 정보 리스트"),
                                                        fieldWithPath("data.subRoutines[].subRoutineId").type(NUMBER).description("서브 루틴 id"),
                                                        fieldWithPath("data.subRoutines[].content").type(STRING).description("서브 루틴 내용"),
                                                        fieldWithPath("data.subRoutines[].detailContent").type(STRING).description("서브 루틴 세부 내용"),
                                                        fieldWithPath("data.subRoutines[].timeTaken").type(STRING).description("소요 시간"),
                                                        fieldWithPath("data.subRoutines[].place").type(STRING).description("장소")
                                                )
                                                .build())))
                .andExpect(status().isOk());
    }
}