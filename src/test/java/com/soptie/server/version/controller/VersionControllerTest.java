package com.soptie.server.version.controller;

import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import com.soptie.server.common.config.ValueConfig;
import com.soptie.server.common.dto.Response;
import com.soptie.server.version.dto.AppVersionResponse;

@WebMvcTest(VersionController.class)
class VersionControllerTest extends BaseControllerTest {

	@MockBean
	VersionController controller;
	@MockBean
	Principal principal;
	@MockBean
	ValueConfig valueConfig;

	private final String DEFAULT_URL = "/api/v1/versions";
	private final String TAG = "VERSION";

	@Test
	@DisplayName("클라이언트 앱 버전 정보 조회 성공")
	void success_getClientAppVersion() throws Exception {
		// given
		AppVersionResponse versionInfo = AppVersionResponse.of(
				"1.0.0",
				"1.0.0",
				"1.0.0",
				"1.0.0",
				"업데이트 안내",
				"업데이트가 필요합니다.");
		ResponseEntity<Response> response = ResponseEntity.ok(Response.success("버전 조회 성공", versionInfo));

		// when
		when(controller.getClientAppVersion()).thenReturn(response);

		// then
		mockMvc
				.perform(
						RestDocumentationRequestBuilders.get(DEFAULT_URL + "/client/app")
								.contentType(MediaType.APPLICATION_JSON)
								.accept(MediaType.APPLICATION_JSON))
				.andDo(
						MockMvcRestDocumentation.document(
								"get-client-app-version-docs",
								preprocessRequest(prettyPrint()),
								preprocessResponse(prettyPrint()),
								resource(
										ResourceSnippetParameters.builder()
												.tag(TAG)
												.description("앱 버전 정보 조회")
												.requestFields()
												.responseFields(
														fieldWithPath("success").type(BOOLEAN).description("응답 성공 여부"),
														fieldWithPath("message").type(STRING).description("응답 메시지"),
														fieldWithPath("data").type(OBJECT).description("응답 데이터"),
														fieldWithPath("data.iosVersion").type(OBJECT).description("iOS 버전 정보"),
														fieldWithPath("data.iosVersion.appVersion").type(STRING).description("iOS 앱 버전"),
														fieldWithPath("data.iosVersion.forceUpdateVersion").type(STRING).description("iOS 강제 업데이트 버전"),
														fieldWithPath("data.androidVersion").type(OBJECT).description("안드로이드 버전 정보"),
														fieldWithPath("data.androidVersion.appVersion").type(STRING).description("안드로이드 앱 버전"),
														fieldWithPath("data.androidVersion.forceUpdateVersion").type(STRING).description("안드로이드 강제 업데이트 버전"),
														fieldWithPath("data.notificationTitle").type(STRING).description("업데이트 알림 제목"),
														fieldWithPath("data.notificationContent").type(STRING).description("업데이트 알림 내용")
												)
												.build())))
				.andExpect(status().isOk());
	}

}