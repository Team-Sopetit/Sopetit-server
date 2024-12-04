package com.soptie.server.domain.theme;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Theme {
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String comment;
	@NotNull
	private String description;
	private int sequence;
	private Long makerId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Theme theme = (Theme)obj;
		return sequence == theme.sequence && Objects.equals(id, theme.id) && Objects.equals(name,
			theme.name) && Objects.equals(comment, theme.comment) && Objects.equals(description,
			theme.description) && Objects.equals(makerId, theme.makerId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, comment, description, sequence, makerId);
	}
}
