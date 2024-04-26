package com.soptie.server.routine.entity;

import static lombok.AccessLevel.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = PROTECTED)
public enum RoutineType {

	DAILY("데일리"),
	CHALLENGE("도전"),
	;

	private final String name;
}
