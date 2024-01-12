package com.soptie.server.member.dto;

import lombok.Builder;

@Builder
public record CottonCountResponse(
        int cottonCount
) {

    public static CottonCountResponse of(int cottonCount) {
        return CottonCountResponse.builder()
                .cottonCount(cottonCount)
                .build();
    }
}
