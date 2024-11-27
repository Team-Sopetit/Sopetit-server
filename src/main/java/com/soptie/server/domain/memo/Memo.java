package com.soptie.server.domain.memo;

import java.time.LocalDate;

import com.soptie.server.domain.member.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {
	private Long id;
	private LocalDate achievedDate;
	private String content;
	private long memberId;

	public Memo(final LocalDate achievedDate, final String content, final Member member) {
		this.achievedDate = achievedDate;
		this.content = content;
		this.memberId = member.getId();
	}

	public void update(final String content) {
		this.content = content;
	}
}
