package com.soptie.server.memberRoutine.controller;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static com.soptie.server.common.dto.Response.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URI;
import java.security.Principal;

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
import com.soptie.server.memberRoutine.dto.AchievedMemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutinesResponse;
import com.soptie.server.memberRoutine.fixture.MemberDailyRoutineFixture;

@WebMvcTest(MemberDailyRoutineController.class)
class MemberDailyRoutineControllerTest extends BaseControllerTest {

	@MockBean
	MemberDailyRoutineController controller;
	@MockBean
	Principal principal;

	private final String DEFAULT_URL = "/api/v1/routines/daily/member";
	private final String TAG = "MEMBER DAILY ROUTINE";

	@Test
	@DisplayName("회원 데일리 루틴 추가 성공")
	void success_createMemberDailyRoutine() throws Exception {
		// given
		MemberDailyRoutineRequest request = new MemberDailyRoutineRequest(1L);
		MemberDailyRoutineResponse savedMemberRoutine = MemberDailyRoutineFixture.createMemberDailyRoutineResponseDTO();
		ResponseEntity<Response> response = ResponseEntity
			.created(URI.create("redirect_uri"))
			.body(success("루틴 추가 성공", savedMemberRoutine));

		// when
		when(controller.createMemberDailyRoutine(principal, request)).thenReturn(response);

		// then
		mockMvc.perform(post(DEFAULT_URL)
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.principal(principal)
				.content(objectMapper.writeValueAsString(request))
			)
			.andDo(
				document("post-routine-docs",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					resource(ResourceSnippetParameters.builder()
						.tag(TAG)
						.description("회원 데일리 루틴 추가 성공")
						.requestFields(
							fieldWithPath("routineId").type(NUMBER).description("추가할 루틴 id")
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
	@DisplayName("회원 데일리 루틴 삭제 성공")
	void success_deleteMemberDailyRoutine() throws Exception {
		// given
		Long routineId = 1L;
		ResponseEntity<Response> response = ResponseEntity.ok(success("루틴 삭제 성공"));

		// when
		when(controller.deleteMemberDailyRoutine(principal, routineId)).thenReturn(response);

		// then
		mockMvc.perform(delete(DEFAULT_URL + "/routine/{routineId}", routineId)
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.principal(principal))
			.andDo(
				document("delete-routine-docs",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					resource(ResourceSnippetParameters.builder()
						.tag(TAG)
						.description("회원 데일리 루틴 삭제 성공")
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
	@DisplayName("루틴 달성 성공")
	void success_achieveMemberDailyRoutine() throws Exception {
		// given
		Long routineId = 1L;
		AchievedMemberDailyRoutineResponse memberRoutine = new AchievedMemberDailyRoutineResponse(
			routineId, true, 1);
		ResponseEntity<Response> response = ResponseEntity.ok(success("루틴 달성 성공", memberRoutine));

		// when
		when(controller.achieveMemberDailyRoutine(principal, routineId)).thenReturn(response);

		// then
		mockMvc.perform(patch(DEFAULT_URL + "/routine/{routineId}", routineId)
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.principal(principal))
			.andDo(
				document("ahieve-routine-docs",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					resource(ResourceSnippetParameters.builder()
						.tag(TAG)
						.description("회원 데일리 루틴 달성 성공")
						.pathParameters(
							parameterWithName("routineId").description("루틴 id")
						)
						.responseFields(
							fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
							fieldWithPath("message").type(STRING).description("응답 메시지"),
							fieldWithPath("data").type(OBJECT).description("응답 데이터"),
							fieldWithPath("data.routineId").type(NUMBER).description("루틴 id"),
							fieldWithPath("data.isAchieve").type(BOOLEAN).description("달성 여부"),
							fieldWithPath("data.achieveCount").type(NUMBER).description("달성 횟수")
						)
						.build()
					)
				))
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("회원 별 데일리 루틴 리스트 조회 성공")
	void success_getMemberDailyRoutines() throws Exception {
		// given
		MemberDailyRoutinesResponse routines = MemberDailyRoutineFixture.createMemberDailyRoutinesResponseDTO();
		ResponseEntity<Response> response = ResponseEntity.ok(Response.success("루틴 조회 성공", routines));

		// when
		when(controller.getMemberDailyRoutine(principal)).thenReturn(response);

		// then
		mockMvc
			.perform(
				RestDocumentationRequestBuilders.get(DEFAULT_URL)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.principal(principal))
			.andDo(
				MockMvcRestDocumentation.document(
					"get-routines-docs",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					resource(
						ResourceSnippetParameters.builder()
							.tag(TAG)
							.description("회원 별 데일리 루틴 리스트 조회")
							.requestFields()
							.responseFields(
								fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
								fieldWithPath("message").type(STRING).description("응답 메시지"),
								fieldWithPath("data").type(OBJECT).description("응답 데이터"),
								fieldWithPath("data.routines").type(ARRAY).description("루틴 정보 리스트"),
								fieldWithPath("data.routines[].routineId").type(NUMBER).description("루틴 id"),
								fieldWithPath("data.routines[].content").type(STRING).description("테마 내용"),
								fieldWithPath("data.routines[].iconImageUrl").type(STRING).description("아이콘 이미지 url"),
								fieldWithPath("data.routines[].achieveCount").type(NUMBER).description("달성 횟수"),
								fieldWithPath("data.routines[].isAchieve").type(BOOLEAN).description("달성 여부")
							)
							.build())))
			.andExpect(status().isOk());
	}
}