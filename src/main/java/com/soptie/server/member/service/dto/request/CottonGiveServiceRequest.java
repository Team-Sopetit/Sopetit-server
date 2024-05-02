package com.soptie.server.member.service.dto.request;

import com.soptie.server.member.entity.CottonType;
import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record CottonGiveServiceRequest(
        long memberId,
        @NonNull CottonType cottonType
) {

    public static CottonGiveServiceRequest of(long memberId, CottonType cottonType) {
        return CottonGiveServiceRequest.builder()
                .memberId(memberId)
                .cottonType(cottonType)
                .build();
    }
}
