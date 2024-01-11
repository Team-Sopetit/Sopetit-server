package com.soptie.server.member.dto;

import com.soptie.server.doll.entity.DollType;

import java.util.List;

public record MemberProfileRequest(
        DollType dollType,
        String name,
        List<Long> routines
) {
}
