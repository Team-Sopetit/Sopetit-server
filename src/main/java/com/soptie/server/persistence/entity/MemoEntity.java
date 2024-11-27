package com.soptie.server.persistence.entity;

import java.time.LocalDate;

import com.soptie.server.domain.member.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "memo", schema = "softie")
public class MemoEntity extends BaseEntity {
	@Column(nullable = false)
	private LocalDate achievedDate;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private long memberId;

	public MemoEntity(final LocalDate achievedDate, final String content, final Member member) {
		this.achievedDate = achievedDate;
		this.content = content;
		this.memberId = member.getId();
	}
}
