package com.soptie.server.api.controller.memberroutine.dto;

import java.time.LocalTime;

public record UpdateMemberRoutineRequest(
	LocalTime alarmTime
) {
}
