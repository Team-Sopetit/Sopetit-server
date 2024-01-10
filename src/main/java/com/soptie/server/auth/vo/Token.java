package com.soptie.server.auth.vo;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Token {

    private final String accessToken;
    private final String refreshToken;

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(accessToken, token.accessToken) && Objects.equals(refreshToken, token.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken);
    }
}
