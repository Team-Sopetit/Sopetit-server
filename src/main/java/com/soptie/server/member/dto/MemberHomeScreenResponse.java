package com.soptie.server.member.dto;

import com.soptie.server.doll.entity.DollImage;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.entity.Cotton;
import lombok.Builder;

import java.util.List;

@Builder
public record MemberHomeScreenResponse(
        String name,
        DollType dollType,
        String frameImageUrl,
        int dailyCottonCount,
        int happinessCottonCount,
        List<String> conversations
) {

    public static MemberHomeScreenResponse of(String name, DollType dollType, String frameImageUrl, Cotton cotton, List<String> conversations) {
        return MemberHomeScreenResponse.builder()
                .name(name)
                .dollType(dollType)
                .frameImageUrl(frameImageUrl)
                .dailyCottonCount(cotton.getDailyCottonCount())
                .happinessCottonCount(cotton.getHappinessCottonCount())
                .conversations(conversations)
                .build();
    }
}
