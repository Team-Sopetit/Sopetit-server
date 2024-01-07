package com.soptie.server.routine.entity.daily;

import jakarta.persistence.Embeddable;

@Embeddable
public class RoutineImage {

	private String coloredIconImageUrl;
	private String uncoloredIconImageUrl;

	private String backgroundImageUrl;
}
