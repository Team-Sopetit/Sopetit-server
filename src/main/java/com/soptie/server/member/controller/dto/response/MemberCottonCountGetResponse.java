package com.soptie.server.member.controller.dto.response;

import com.soptie.server.member.service.dto.response.MemberCottonCountGetServiceResponse;
import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberCottonCountGetResponse(
        int cottonCount
) {

    public static MemberCottonCountGetResponse of(MemberCottonCountGetServiceResponse response) {
        return MemberCottonCountGetResponse.builder()
                .cottonCount(response.cottonCount())
                .build();
    }
}
