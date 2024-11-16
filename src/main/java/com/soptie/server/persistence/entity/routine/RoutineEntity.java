package com.soptie.server.persistence.entity.routine;

import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "routine", schema = "softie")
public class RoutineEntity extends BaseEntity {
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private long themeId;

	public RoutineEntity(Routine routine) {
		this.content = routine.getContent();
		this.themeId = routine.getThemeId();
	}

	public Routine toDomain() {
		return Routine.builder()
			.id(this.id)
			.content(this.content)
			.themeId(this.themeId)
			.build();
	}
}
