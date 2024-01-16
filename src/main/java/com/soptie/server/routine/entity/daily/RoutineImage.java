package com.soptie.server.routine.entity.daily;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class RoutineImage {

	private String iconImageUrl;

	private String backgroundImageUrl;
}
