package com.soptie.server.member.controller.dto.response;

import com.soptie.server.member.service.dto.response.CottonCountGetServiceResponse;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record CottonCountGetResponse(
        int cottonCount
) {

    public static CottonCountGetResponse of(CottonCountGetServiceResponse response) {
        return CottonCountGetResponse.builder()
                .cottonCount(response.cottonCount())
                .build();
    }
}
