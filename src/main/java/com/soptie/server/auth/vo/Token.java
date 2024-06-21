package com.soptie.server.auth.vo;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Token {

	private final String accessToken;
	private final String refreshToken;

	@Builder
	public Token(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		Token token = (Token)object;
		return Objects.equals(accessToken, token.accessToken) && Objects.equals(refreshToken, token.refreshToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken, refreshToken);
	}
}
