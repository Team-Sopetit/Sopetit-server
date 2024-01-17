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

import java.security.Principal;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.soptie.server.auth.message.SuccessMessage.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
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
    @MockBean
    Principal principal;

    private final String DEFAULT_URL = "/api/v1/auth";
    private final String TAG = "AUTH";

    @Test
    @DisplayName("소셜 로그인 성공")
    void success_getTokenBySocialAccessToken() throws Exception {
        // given
        String socialAccessToken = "Bearer softietoken";
        SignInRequest request = SignInRequest.of(SocialType.KAKAO);
        SignInResponse response = SignInResponse.of(
                Token.builder()
                .accessToken("softie")
                .refreshToken("token")
                .build(), false
        );
        ResponseEntity<Response> result = ResponseEntity.ok(Response.success(SUCCESS_SIGN_IN.getMessage(), response));

        // when
        when(controller.signIn(socialAccessToken, request)).thenReturn(result);

        // then
        mockMvc
                .perform(
                        post(DEFAULT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", socialAccessToken)
                                .content(objectMapper.writeValueAsString(request)))
                .andDo(
                        document(
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
                                                        fieldWithPath("data.refreshToken").type(STRING).description("Refresh Token"),
                                                        fieldWithPath("data.isMemberDollExist").type(BOOLEAN).description("프로필 존재 여부")
                                                )
                                                .build())))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그아웃 성공")
    void success_signOut() throws Exception {
        // given
        ResponseEntity<Response> result = ResponseEntity.ok(Response.success(SUCCESS_SIGN_OUT.getMessage(), null));

        // when
        when(controller.signOut(principal)).thenReturn(result);

        // then
        mockMvc
                .perform(
                        post(DEFAULT_URL + "/logout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .principal(principal)
                )
                .andDo(
                        document(
                                "post-logout-docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag(TAG)
                                                .description("로그아웃")
                                                .responseFields(
                                                        fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
                                                        fieldWithPath("message").type(STRING).description("응답 메시지"),
                                                        fieldWithPath("data").type(NULL).description("응답 데이터")
                                                )
                                                .build())))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원탈퇴 성공")
    void success_withdrawal() throws Exception {
        // given
        ResponseEntity<Response> result = ResponseEntity.ok(Response.success(SUCCESS_WITHDRAWAL.getMessage()));

        // when
        when(controller.withdrawal(principal)).thenReturn(result);

        // then
        mockMvc
                .perform(
                        delete(DEFAULT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .principal(principal)
                )
                .andDo(
                        document(
                                "delete-withdrawal-docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag(TAG)
                                                .description("회원탈퇴")
                                                .responseFields(
                                                        fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
                                                        fieldWithPath("message").type(STRING).description("응답 메시지"),
                                                        fieldWithPath("data").type(NULL).description("응답 데이터")
                                                )
                                                .build())))
                .andExpect(status().isOk());
    }
}
