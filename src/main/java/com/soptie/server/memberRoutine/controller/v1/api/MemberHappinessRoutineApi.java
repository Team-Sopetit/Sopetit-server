package com.soptie.server.memberRoutine.controller.v1.api;

import com.soptie.server.common.dto.BaseResponse;
import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.memberRoutine.controller.v1.dto.request.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.controller.v1.dto.response.MemberHappinessRoutineCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Tag(name = "member happiness routines V1", description = "회원의 행복 루틴 API Version1")
public interface MemberHappinessRoutineApi {

    @Operation(
            summary = "행복 루틴 추가",
            description = "회원의 행복 루틴을 추가한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "유효하지 않은 토큰",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<SuccessResponse<MemberHappinessRoutineCreateResponse>> createMemberHappinessRoutine(
            @Parameter(hidden = true) Principal principal,
            @RequestBody MemberHappinessRoutineRequest request
    );

    @Operation(
            summary = "회원의 행복 루틴 목록 조회",
            description = "회원의 행복 루틴을 조회한다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "데이터 없음",
                            content = @Content(schema = @Schema(implementation = BaseResponse.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "유효하지 않은 토큰",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<?> getMemberHappinessRoutine(@Parameter(hidden = true) Principal principal);

    @Operation(
            summary = "행복 루틴 삭제",
            description = "회원의 행복 루틴을 삭제한다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "유효하지 않은 토큰",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<BaseResponse> deleteMemberHappinessRoutine(
            @Parameter(hidden = true) Principal principal,
            @Parameter(
                    name = "routineId",
                    description = "삭제할 회원의 행복 루틴 id",
                    in = ParameterIn.PATH,
                    example = "1"
            )
            @PathVariable Long routineId
    );

    @Operation(
            summary = "행복 루틴 달성",
            description = "회원의 행복 루틴을 달성한다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "유효하지 않은 토큰",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "4xx",
                            description = "클라이언트(요청) 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 내부 오류",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    ResponseEntity<BaseResponse> achieveMemberHappinessRoutine(
            @Parameter(hidden = true) Principal principal,
            @Parameter(
                    name = "routineId",
                    description = "달성한 회원의 행복 루틴 id",
                    in = ParameterIn.PATH,
                    example = "1"
            )
            @PathVariable Long routineId
    );
}
