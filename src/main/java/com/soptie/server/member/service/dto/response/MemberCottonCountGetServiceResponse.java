package com.soptie.server.member.service.dto.response;

import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberCottonCountGetServiceResponse(
        int cottonCount
) {

    public static MemberCottonCountGetServiceResponse of(int cottonCount) {
        return MemberCottonCountGetServiceResponse.builder()
                .cottonCount(cottonCount)
                .build();
    }
}
