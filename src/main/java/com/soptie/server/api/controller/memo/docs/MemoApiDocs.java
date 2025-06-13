package com.soptie.server.api.controller.memo.docs;

import java.security.Principal;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.soptie.server.api.common.annotation.ApiCreatedResponse;
import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.memo.dto.CreateMemoRequest;
import com.soptie.server.api.controller.memo.dto.CreateMemoResponse;
import com.soptie.server.api.controller.memo.dto.ModifyMemoRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.MEMO_NAME, description = ApiTagConstants.MEMO_DESCRIPTION)
public interface MemoApiDocs {

	@Operation(summary = "메모 생성", description = "메모를 생성한다.")
	@ApiCreatedResponse
	SuccessResponse<CreateMemoResponse> createMemo(
		@Parameter(hidden = true)
		Principal principal,

		@RequestBody
		CreateMemoRequest request
	);

	@Operation(summary = "메모 수정", description = "메모를 수정한다.")
	@ApiSuccessResponse
	SuccessResponse<?> modifyMemo(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "memoId",
			description = "수정할 메모 id",
			in = ParameterIn.PATH,
			example = "1"
		) @PathVariable final long memoId,

		@RequestBody
		ModifyMemoRequest request
	);

	@Operation(summary = "메모 삭제", description = "메모를 삭제한다.")
	@ApiSuccessResponse
	SuccessResponse<?> deleteMemo(
		@Parameter(hidden = true)
		Principal principal,

		@Parameter(
			name = "memoId",
			description = "삭제할 메모 id",
			in = ParameterIn.PATH,
			example = "1") @PathVariable final long memoId
	);
}
