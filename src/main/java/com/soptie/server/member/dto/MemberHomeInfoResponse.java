package com.soptie.server.member.dto;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.entity.Member;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record MemberHomeInfoResponse(
        @NonNull String name,
        @NonNull DollType dollType,
        @NonNull String frameImageUrl,
        int dailyCottonCount,
        int happinessCottonCount,
        @NonNull List<String> conversations
) {

    public static MemberHomeInfoResponse of(Member member, List<String> conversations) {
        return MemberHomeInfoResponse.builder()
                .name(member.getMemberDoll().getName())
                .dollType(member.getMemberDoll().getDoll().getDollType())
                .frameImageUrl(member.getMemberDoll().getDoll().getImageInfo().getFrameImageUrl())
                .dailyCottonCount(member.getCottonInfo().getDailyCottonCount())
                .happinessCottonCount(member.getCottonInfo().getHappinessCottonCount())
                .conversations(conversations)
                .build();
    }
}
