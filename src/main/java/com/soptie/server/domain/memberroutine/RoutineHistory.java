package com.soptie.server.domain.memberroutine;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RoutineHistory {
	private Long id;
	private long memberRoutineId;
	private long memberId;
	private long routineId;
	private String content;
	private LocalDateTime createdAt;
}
