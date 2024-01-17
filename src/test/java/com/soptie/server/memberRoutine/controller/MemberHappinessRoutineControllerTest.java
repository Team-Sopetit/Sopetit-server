package com.soptie.server.memberRoutine.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.soptie.server.base.BaseControllerTest;
import com.soptie.server.common.dto.Response;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutinesResponse;
import com.soptie.server.memberRoutine.fixture.MemberHappinessRoutineFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

import java.net.URI;
import java.security.Principal;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.soptie.server.common.dto.Response.success;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberHappinessRoutineController.class)
class MemberHappinessRoutineControllerTest extends BaseControllerTest {

	@MockBean
	MemberHappinessRoutineController controller;
	@MockBean
	Principal principal;

	private final String DEFAULT_URL = "/api/v1/routines/happiness/member";
	private final String TAG = "MEMBER HAPPINESS ROUTINE";

	@Test
	@DisplayName("회원 별 행복 루틴 조회 성공")
	void success_getMemberHappinessRoutines() throws Exception {
		// given
		MemberHappinessRoutinesResponse routines = MemberHappinessRoutineFixture.createMemberHappinessRoutinesResponseDTO();
		ResponseEntity<Response> response = ResponseEntity.ok(Response.success("루틴 조회 성공", routines));

		// when
		when(controller.getMemberHappinessRoutine(principal)).thenReturn(response);

		// then
		mockMvc
				.perform(
						RestDocumentationRequestBuilders.get(DEFAULT_URL)
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON)
								.principal(principal))
				.andDo(
						MockMvcRestDocumentation.document(
								"get-member-happiness-routine-docs",
								preprocessRequest(prettyPrint()),
								preprocessResponse(prettyPrint()),
								resource(
										ResourceSnippetParameters.builder()
												.tag(TAG)
												.description("회원 별 행복 루틴 리스트 조회")
												.requestFields()
												.responseFields(
														fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
														fieldWithPath("message").type(STRING).description("응답 메시지"),
														fieldWithPath("data").type(OBJECT).description("응답 데이터"),
														fieldWithPath("data.routineId").type(NUMBER).description("루틴 id"),
														fieldWithPath("data.iconImageUrl").type(STRING).description("아이콘 이미지 url"),
														fieldWithPath("data.contentImageUrl").type(STRING).description("카드 이미지 url"),
														fieldWithPath("data.themeName").type(STRING).description("테마 이름"),
														fieldWithPath("data.themeNameColor").type(STRING).description("테마 이름 색상"),
														fieldWithPath("data.title").type(STRING).description("루틴 제목"),
														fieldWithPath("data.content").type(STRING).description("루틴 내용"),
														fieldWithPath("data.detailContent").type(STRING).description("루틴 상세 내용"),
														fieldWithPath("data.place").type(STRING).description("장소"),
														fieldWithPath("data.timeTaken").type(STRING).description("소요 시간")
												)
												.build())))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("회원 행복 루틴 추가 성공")
	void success_createMemberHappinessRoutine() throws Exception {

		MemberHappinessRoutineRequest request = new MemberHappinessRoutineRequest(1L);
		MemberHappinessRoutineResponse savedMemberRoutine = MemberHappinessRoutineFixture.createMemberHappinessRoutineResponseDTO();
		ResponseEntity<Response> response = ResponseEntity
				.created(URI.create("redirect_uri"))
				.body(success("루틴 추가 성공", savedMemberRoutine));

		when(controller.createMemberHappinessRoutine(principal, request)).thenReturn(response);

		mockMvc.perform(post(DEFAULT_URL)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)
						.principal(principal)
						.content(objectMapper.writeValueAsString(request))
				)
				.andDo(
						document("post-member-happiness-routine-docs",
								preprocessRequest(prettyPrint()),
								preprocessResponse(prettyPrint()),
								resource(ResourceSnippetParameters.builder()
										.tag(TAG)
										.description("회원 행복 루틴 추가 성공")
										.requestFields(
												fieldWithPath("subRoutineId").type(NUMBER).description("추가할 루틴 id")
										)
										.responseHeaders(
												headerWithName("Location").description("Redirect URI")
										)
										.responseFields(
												fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
												fieldWithPath("message").type(STRING).description("응답 메시지"),
												fieldWithPath("data").type(OBJECT).description("응답 데이터"),
												fieldWithPath("data.routineId").type(NUMBER).description("생성한 루틴 id")
										)
										.build()
								)
						))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("회원 행복 루틴 삭제 성공")
	void success_deleteMemberHappinessRoutine() throws Exception {

		Long routineId = 1L;
		ResponseEntity<Response> response = ResponseEntity.ok(success("루틴 삭제 성공"));

		when(controller.deleteMemberHappinessRoutine(principal, routineId)).thenReturn(response);

		mockMvc.perform(delete(DEFAULT_URL + "/routine/{routineId}", routineId)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)
						.principal(principal))
				.andDo(
						document("delete-member-happiness-routine-docs",
								preprocessRequest(prettyPrint()),
								preprocessResponse(prettyPrint()),
								resource(ResourceSnippetParameters.builder()
										.tag(TAG)
										.description("회원 행복 루틴 삭제 성공")
										.pathParameters(
												parameterWithName("routineId").description("루틴 id")
										)
										.responseFields(
												fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
												fieldWithPath("message").type(STRING).description("응답 메시지"),
												fieldWithPath("data").type(NULL).description("응답 데이터")
										)
										.build()
								)
						))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("회원 행복 루틴 달성 성공")
	void success_achieveMemberHappinessRoutine() throws Exception {

		Long routineId = 1L;
		ResponseEntity<Response> response = ResponseEntity.ok(success("회원 행복 루틴 달성 성공"));

		when(controller.achieveMemberHappinessRoutine(principal, routineId)).thenReturn(response);

		mockMvc.perform(patch(DEFAULT_URL + "/routine/{routineId}", routineId)
						.contentType(APPLICATION_JSON)
						.accept(APPLICATION_JSON)
						.principal(principal))
				.andDo(
						document("achieve-member-happiness-routine-docs",
								preprocessRequest(prettyPrint()),
								preprocessResponse(prettyPrint()),
								resource(ResourceSnippetParameters.builder()
										.tag(TAG)
										.description("회원 행복 루틴 달성 성공")
										.pathParameters(
												parameterWithName("routineId").description("루틴 id")
										)
										.responseFields(
												fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
												fieldWithPath("message").type(STRING).description("응답 메시지"),
												fieldWithPath("data").type(NULL).description("응답 데이터")
										)
										.build()
								)
						))
				.andExpect(status().isOk());
	}
}