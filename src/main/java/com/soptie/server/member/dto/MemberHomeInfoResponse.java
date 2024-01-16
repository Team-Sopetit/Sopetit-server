package com.soptie.server.member.dto;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.entity.Member;
import lombok.Builder;

import java.util.List;

@Builder
public record MemberHomeInfoResponse(
        String name,
        DollType dollType,
        String frameImageUrl,
        int dailyCottonCount,
        int happinessCottonCount,
        List<String> conversations
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
