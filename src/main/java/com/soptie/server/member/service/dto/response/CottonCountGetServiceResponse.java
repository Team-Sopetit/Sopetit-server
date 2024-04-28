package com.soptie.server.member.service.dto.response;

import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record CottonCountGetServiceResponse(
        int cottonCount
) {

    public static CottonCountGetServiceResponse of(int cottonCount) {
        return CottonCountGetServiceResponse.builder()
                .cottonCount(cottonCount)
                .build();
    }
}
