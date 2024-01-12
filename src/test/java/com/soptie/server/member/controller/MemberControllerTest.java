package com.soptie.server.member.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.soptie.server.base.BaseControllerTest;
import com.soptie.server.common.dto.Response;
import com.soptie.server.member.dto.CottonCountResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.CottonType;
import com.soptie.server.routine.dto.DailyRoutinesResponse;
import com.soptie.server.routine.fixture.DailyRoutineFixture;
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

import java.net.URI;
import java.security.Principal;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.soptie.server.common.dto.Response.success;
import static com.soptie.server.doll.entity.DollType.BROWN;
import static com.soptie.server.member.entity.CottonType.DAILY;
import static com.soptie.server.member.message.ResponseMessage.SUCCESS_CREATE_PROFILE;
import static com.soptie.server.member.message.ResponseMessage.SUCCESS_GIVE_COTTON;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest extends BaseControllerTest {

    @MockBean
    MemberController controller;
    @MockBean
    Principal principal;

    private final String DEFAULT_URL = "/api/v1/members";
    private final String TAG = "MEMBER";

    @Test
    @DisplayName("프로필을 생성한다.")
    void success_createMemberProfile() throws Exception {
        // given
        MemberProfileRequest request = new MemberProfileRequest(BROWN, "소프티", List.of(1L, 2L, 3L));
        ResponseEntity<Response> response = ResponseEntity.created(URI.create("redirect_uri"))
                        .body(success(SUCCESS_CREATE_PROFILE.getMessage(), null));

        // when
        when(controller.createMemberProfile(principal, request)).thenReturn(response);

        // then
        mockMvc.perform(post(DEFAULT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(principal)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(
                        MockMvcRestDocumentation.document(
                                "post-member-profile-docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag(TAG)
                                                .description("프로필 생성")
                                                .requestFields(
                                                        fieldWithPath("dollType").type(STRING).description("인형 종류"),
                                                        fieldWithPath("name").type(STRING).description("인형 이름"),
                                                        fieldWithPath("routines").type(ARRAY).description("인형 종류")
                                                )
                                                .responseHeaders(
                                                        headerWithName("Location").description("Redirect URI")
                                                )
                                                .responseFields(
                                                        fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
                                                        fieldWithPath("message").type(STRING).description("응답 메시지"),
                                                        fieldWithPath("data").type(NULL).description("응답 데이터")
                                                )
                                                .build())))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("솜뭉치 주기")
    void success_giveCotton() throws Exception {
        // given
        CottonCountResponse response = CottonCountResponse.of(0);
        ResponseEntity<Response> result = ResponseEntity.ok(Response.success(SUCCESS_GIVE_COTTON.getMessage(), response));

        // when
        when(controller.giveCotton(principal, DAILY)).thenReturn(result);

        // then
        mockMvc
                .perform(
                        RestDocumentationRequestBuilders.patch(DEFAULT_URL + "/{cottonType}", DAILY)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .principal(principal))
                .andDo(
                        MockMvcRestDocumentation.document(
                                "give-cotton-docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag(TAG)
                                                .description("솜뭉치 주기")
                                                .pathParameters(
                                                        parameterWithName("cottonType").description("솜뭉치 종류")
                                                )
                                                .requestFields()
                                                .responseFields(
                                                        fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
                                                        fieldWithPath("message").type(STRING).description("응답 메시지"),
                                                        fieldWithPath("data").type(OBJECT).description("응답 데이터"),
                                                        fieldWithPath("data.cottonCount").type(NUMBER).description("남은 솜뭉치 개수")
                                                )
                                                .build())))
                .andExpect(status().isOk());
    }
}
