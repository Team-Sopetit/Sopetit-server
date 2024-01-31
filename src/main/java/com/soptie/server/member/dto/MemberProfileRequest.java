package com.soptie.server.member.dto;

import com.soptie.server.doll.entity.DollType;

import java.util.List;

import lombok.NonNull;

public record MemberProfileRequest(
        @NonNull DollType dollType,
        @NonNull String name,
        @NonNull List<Long> routines
) {
}
