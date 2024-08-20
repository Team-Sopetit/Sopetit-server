package com.soptie.server.persistence.adapter;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.member.Member;
import com.soptie.server.persistence.entity.MemberEntity;
import com.soptie.server.persistence.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberAdapter {
	private final MemberRepository memberRepository;

	public void deleteById(long memberId) {
		val member = find(memberId);
		//TODO: delete with 연관관계
		memberRepository.delete(member);
	}

	public Member findById(long memberId) {
		return find(memberId).toDomain();
	}

	public void update(Member member) {
		val memberEntity = find(member.getId());
		memberEntity.update(member);
	}

	private MemberEntity find(long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Member ID: " + id));
	}
}
