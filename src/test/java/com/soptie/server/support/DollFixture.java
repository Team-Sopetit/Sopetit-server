package com.soptie.server.support;

import com.soptie.server.doll.entity.Doll;

public class DollFixture {

    private Long id;
    private String faceImageUrl;

    private DollFixture() {
    }

    public static DollFixture doll() {
        return new DollFixture();
    }

    public DollFixture id(Long id) {
        this.id = id;
        return this;
    }

    public DollFixture faceImageUrl(String faceImageUrl) {
        this.faceImageUrl = faceImageUrl;
        return this;
    }

    public Doll build() {
        return new Doll(id, faceImageUrl);
    }
}
