package com.soptie.server.theme.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class ThemeImageInfo {

	private String iconImageUrl;

	private String backgroundImageUrl;
}
