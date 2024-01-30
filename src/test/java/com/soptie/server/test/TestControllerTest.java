package com.soptie.server.test;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.soptie.server.base.BaseControllerTest;
import com.soptie.server.common.config.ValueConfig;

@WebMvcTest(TestController.class)
public class TestControllerTest extends BaseControllerTest {

	@MockBean
	TestController testController;
	@MockBean
	ValueConfig valueConfig;

	private final String DEFAULT_URL = "/api/v1/test";
	private final String TAG = "TEST";

	@Test
	@DisplayName("서버 연결 테스트")
	void success_test() throws Exception {
		// given
		ResponseEntity<String> response = ResponseEntity.ok("Success to server connect.");

		//when
		when(testController.test()).thenReturn(response);

		//then
		mockMvc
			.perform(
				RestDocumentationRequestBuilders.get(DEFAULT_URL)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andDo(
				MockMvcRestDocumentation.document(
					"test-docs",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					resource(
						ResourceSnippetParameters.builder()
							.tag(TAG)
							.description("서버 연결 테스트")
							.requestFields()
							.responseFields()
							.build())))
			.andExpect(
				MockMvcResultMatchers.status().isOk());
	}
}
