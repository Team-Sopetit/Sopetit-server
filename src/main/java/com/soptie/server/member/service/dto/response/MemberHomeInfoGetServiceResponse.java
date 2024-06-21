package com.soptie.server.member.service.dto.response;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.entity.Member;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberHomeInfoGetServiceResponse(
        @NonNull String name,
        @NonNull DollType dollType,
        @NonNull String frameImageUrl,
        int dailyCottonCount,
        int happinessCottonCount,
        @NonNull List<String> conversations
) {

    public static MemberHomeInfoGetServiceResponse of(Member member, List<String> conversations) {
        return MemberHomeInfoGetServiceResponse.builder()
                .name(member.getMemberDoll().getName())
                .dollType(member.getMemberDoll().getDoll().getDollType())
                .frameImageUrl(member.getMemberDoll().getDoll().getImageLinks().getFrameImageUrl())
                .dailyCottonCount(member.getCottonInfo().getDailyCottonCount())
                .happinessCottonCount(member.getCottonInfo().getHappinessCottonCount())
                .conversations(conversations)
                .build();
    }
}
