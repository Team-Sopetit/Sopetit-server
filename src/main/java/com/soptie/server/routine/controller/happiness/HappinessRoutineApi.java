package com.soptie.server.routine.controller.happiness;

import com.soptie.server.common.dto.ErrorResponse;
import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.routine.controller.happiness.dto.HappinessRoutinesResponse;
import com.soptie.server.routine.controller.happiness.dto.HappinessSubRoutinesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "happiness routines", description = "행복 루틴 API")
public interface HappinessRoutineApi {

    @Operation(
            summary = "테마 목록별 행복 루틴 목록 조회",
            description = "테마 목록 중 해당되는 행복 루틴 목록을 조회한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
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
    ResponseEntity<SuccessResponse<HappinessRoutinesResponse>> getHappinessRoutinesByThemes(
            @Parameter(
                    name = "themeId",
                    description = "조회할 행복 루틴 테마 id",
                    in = ParameterIn.QUERY,
                    example = "1"
            ) @RequestParam(required = false) Long themeId
    );

    @Operation(
            summary = "루틴 별 행복 서브 루틴 내용 조회",
            description = "루틴에 해당되는 행복 서브 루틴 내용을 조회한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공"),
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
    ResponseEntity<SuccessResponse<HappinessSubRoutinesResponse>> getHappinessSubRoutinesByRoutineOfTheme(
            @Parameter(
                    name = "routineId",
                    description = "조회할 서브 행복 루틴 id",
                    in = ParameterIn.PATH,
                    example = "1"
            ) @PathVariable long routineId
    );
}
