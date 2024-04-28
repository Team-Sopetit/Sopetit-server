package com.soptie.server.member.service.dto.request;

import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberHomeInfoGetServiceRequest(
        long memberId
) {

    public static MemberHomeInfoGetServiceRequest of(long memberId) {
        return MemberHomeInfoGetServiceRequest.builder()
                .memberId(memberId)
                .build();
    }
}
