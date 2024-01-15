package com.soptie.server.routine.entity.happiness;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoutineImage {

	private String iconImageUrl;

	private String contentImageUrl;
}
