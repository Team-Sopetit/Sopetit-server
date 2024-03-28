package com.soptie.server.support;

import com.soptie.server.routine.entity.happiness.HappinessTheme;

public class HappinessThemeFixture {
    private Long id;

    private String name;
    private String nameColor;
    private String iconImageUrl;
    private String contentImageUrl;
    private String twinkleIconImageUrl;

    private HappinessThemeFixture() {
    }
    public static HappinessThemeFixture happinessTheme() {
        return new HappinessThemeFixture();
    }
    public HappinessThemeFixture id(Long id) {
        this.id = id;
        return this;
    }

    public HappinessThemeFixture name(String name) {
        this.name = name;
        return this;
    }

    public HappinessThemeFixture nameColor(String nameColor) {
        this.nameColor = nameColor;
        return this;
    }

    public HappinessThemeFixture imageUrl(String imageUrl) {
        this.iconImageUrl = imageUrl;
        this.contentImageUrl = imageUrl;
        this.twinkleIconImageUrl = imageUrl;
        return this;
    }


    public HappinessTheme build() {
        return new HappinessTheme(id, name, nameColor, iconImageUrl, contentImageUrl, twinkleIconImageUrl);
    }
}
