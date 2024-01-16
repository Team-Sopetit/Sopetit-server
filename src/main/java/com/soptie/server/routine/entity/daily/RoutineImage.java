package com.soptie.server.routine.entity.daily;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RoutineImage {

	private String iconImageUrl;

	private String backgroundImageUrl;
}
