package com.soptie.server.persistence.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.member.SocialType;
import com.soptie.server.persistence.converter.MemberConverter;
import com.soptie.server.persistence.entity.MemberEntity;
import com.soptie.server.persistence.repository.MemberRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberAdapter {

	private final MemberRepository memberRepository;

	public Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId) {
		return memberRepository.findBySocialTypeAndSocialId(socialType, socialId)
			.map(MemberConverter::convert);
	}

	public Member save(Member member) {
		Member savedMember = MemberConverter.convert(memberRepository.save(new MemberEntity(member)));
		savedMember.setNewMember(true);
		return savedMember;
	}

	public Member findByRefreshToken(String refreshToken) {
		return memberRepository.findByRefreshToken(refreshToken)
			.map(MemberConverter::convert)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND));
	}

	public void delete(long memberId) {
		memberRepository.deleteById(memberId);
	}

	public Member findById(long memberId) {
		return MemberConverter.convert(find(memberId));
	}

	public boolean existsById(long memberId) {
		return memberRepository.existsById(memberId);
	}

	public void update(Member member) {
		find(member.getId()).update(member);
	}

	public List<Member> findAllByFcmTokenIsNotNull() {
		return memberRepository.findAllByFcmTokenIsNotNull()
			.stream()
			.map(MemberConverter::convert)
			.toList();
	}

	public List<Member> findByIdIn(List<Long> ids) {
		return memberRepository.findByIdIn(ids)
			.stream()
			.map(MemberConverter::convert)
			.toList();
	}

	public List<Member> findAllByFcmTokenIsNotNullAndLastVisitDateBefore(LocalDate thresholdDate) {
		return memberRepository.findAllByFcmTokenIsNotNullAndLastVisitDateBefore(thresholdDate)
			.stream()
			.map(MemberConverter::convert)
			.toList();
	}

	public long countAll() {
		return memberRepository.count();
	}

	@NonNull
	private MemberEntity find(long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Member ID: " + id));
	}
}
