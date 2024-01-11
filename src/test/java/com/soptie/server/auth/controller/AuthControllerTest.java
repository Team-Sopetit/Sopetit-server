package com.soptie.server.auth.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.soptie.server.auth.dto.SignInRequest;
import com.soptie.server.auth.dto.SignInResponse;
import com.soptie.server.auth.vo.Token;
import com.soptie.server.base.BaseControllerTest;
import com.soptie.server.common.dto.Response;
import com.soptie.server.member.entity.SocialType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest extends BaseControllerTest {

    @MockBean
    AuthController controller;

    private final String DEFAULT_URL = "/api/v1/auth";
    private final String TAG = "AUTH";

    @Test
    @DisplayName("socialAccessToken이 유효한 지 검증한 후, 유효하다면 회원 정보를 얻어온다. 그 후, 회원 정보를 토대로 Token Vo를 반환해준다.")
    void success_getTokenBySocialAccessToken() throws Exception {
        // given
        String socialAccessToken = "Bearer softietoken";
        SignInRequest request = SignInRequest.of(SocialType.KAKAO);
        SignInResponse response = SignInResponse.of(
                Token.builder()
                .accessToken("softie")
                .refreshToken("token")
                .build()
        );
        ResponseEntity<Response> result = ResponseEntity.ok(Response.success("소셜로그인 성공", response));

        // when
        when(controller.signIn(socialAccessToken, request)).thenReturn(result);

        // then
        mockMvc
                .perform(
                        RestDocumentationRequestBuilders.post(DEFAULT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", socialAccessToken)
                                .content(objectMapper.writeValueAsString(request)))
                .andDo(
                        MockMvcRestDocumentation.document(
                                "post-token-docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag(TAG)
                                                .description("소셜 로그인")
                                                .requestHeaders(
                                                        headerWithName("Authorization").description("Social Access Token")
                                                )
                                                .requestFields(
                                                        fieldWithPath("socialType").type(STRING).description("소셜 종류"))
                                                .responseFields(
                                                        fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
                                                        fieldWithPath("message").type(STRING).description("응답 메시지"),
                                                        fieldWithPath("data").type(OBJECT).description("응답 데이터"),
                                                        fieldWithPath("data.accessToken").type(STRING).description("Access Token"),
                                                        fieldWithPath("data.refreshToken").type(STRING).description("Refresh Token")
                                                )
                                                .build())))
                .andExpect(status().isOk());
    }
}
