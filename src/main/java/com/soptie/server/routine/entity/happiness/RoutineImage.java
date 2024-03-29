package com.soptie.server.routine.entity.happiness;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class RoutineImage {

	private String iconImageUrl;

	private String contentImageUrl;

	private String twinkleIconImageUrl;
}
