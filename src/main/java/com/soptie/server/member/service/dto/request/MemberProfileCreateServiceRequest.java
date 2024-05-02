package com.soptie.server.member.service.dto.request;

import com.soptie.server.doll.entity.DollType;

import java.util.List;

import com.soptie.server.member.controller.dto.request.MemberProfileCreateRequest;
import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberProfileCreateServiceRequest(
        long memberId,
        @NonNull DollType dollType,
        @NonNull String name,
        @NonNull List<Long> routines
) {

    public static MemberProfileCreateServiceRequest of(long memberId, MemberProfileCreateRequest request) {
        return MemberProfileCreateServiceRequest.builder()
                .memberId(memberId)
                .dollType(request.dollType())
                .name(request.name())
                .routines(request.routines())
                .build();
    }
}
