package com.soptie.server.member.dto;

import com.soptie.server.doll.entity.DollType;

import java.util.List;

public record MemberProfileRequest(
        DollType dollType,
        String name,
        List<Long> routines
) {

    public static MemberProfileRequest of(DollType dollType, String name, List<Long> routines) {
        return new MemberProfileRequest(dollType, name, routines);
    }
}
