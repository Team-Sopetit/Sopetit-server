package com.soptie.server.member.controller.dto.request;

import com.soptie.server.doll.entity.DollType;
import lombok.NonNull;

import java.util.List;

public record MemberProfileCreateRequest(
        @NonNull DollType dollType,
        @NonNull String name,
        @NonNull List<Long> routines
) {
}
