package com.soptie.server.domain.memo;

import java.time.LocalDate;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
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

	public void updateContent(final String content) {
		this.content = content;
	}

	public void validateMember(final long memberId) {
		if (this.memberId != memberId) {
			throw new SoftieException(ExceptionCode.UNAUTHORIZED, "접근할 수 없는 메모입니다.");
		}
	}
}
