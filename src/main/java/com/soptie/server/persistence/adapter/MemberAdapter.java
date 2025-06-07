package com.soptie.server.persistence.adapter;

import java.util.List;
import java.util.Optional;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.member.SocialType;
import com.soptie.server.persistence.entity.MemberEntity;
import com.soptie.server.persistence.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberAdapter {

	private final MemberRepository memberRepository;

	public Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId) {
		return memberRepository.findBySocialTypeAndSocialId(socialType, socialId)
			.map(MemberEntity::toDomain);
	}

	public Member save(SocialType socialType, String socialId) {
		return memberRepository.save(new MemberEntity(socialType, socialId)).toDomain();
	}

	public Member findByRefreshToken(String refreshToken) {
		return memberRepository.findByRefreshToken(refreshToken)
			.map(MemberEntity::toDomain)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND));
	}

	public void delete(long memberId) {
		memberRepository.deleteById(memberId);
	}

	public Member findById(long memberId) {
		return find(memberId).toDomain();
	}

	public void update(Member member) {
		val memberEntity = find(member.getId());
		memberEntity.update(member);
	}

	public List<Member> findByIds(List<Long> ids) {
		return memberRepository.findByIdIn(ids)
			.stream()
			.map(MemberEntity::toDomain)
			.toList();
	}

	private MemberEntity find(long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Member ID: " + id));
	}
}
