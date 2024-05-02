package com.soptie.server.support.fixture;

import com.soptie.server.doll.entity.Doll;
import com.soptie.server.memberDoll.entity.MemberDoll;

public class MemberDollFixture {

    private Long id;
    private String name;
    private int happinessCottonCount;
    private Doll doll;

    public MemberDollFixture() {
    }

    public static MemberDollFixture memberDoll() {
        return new MemberDollFixture();
    }

    public MemberDollFixture id(Long id) {
        this.id = id;
        return this;
    }

    public MemberDollFixture name(String name) {
        this.name = name;
        return this;
    }

    public MemberDollFixture happinessCottonCount(int happinessCottonCount) {
        this.happinessCottonCount = happinessCottonCount;
        return this;
    }

    public MemberDollFixture doll(Doll doll) {
        this.doll = doll;
        return this;
    }

    public MemberDoll build() {
        return new MemberDoll(id, name, happinessCottonCount, doll);
    }
}
