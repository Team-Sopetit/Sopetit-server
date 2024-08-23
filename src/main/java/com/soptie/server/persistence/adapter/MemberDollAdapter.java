package com.soptie.server.persistence.adapter;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.memberdoll.MemberDoll;
import com.soptie.server.persistence.entity.MemberDollEntity;
import com.soptie.server.persistence.repository.MemberDollRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberDollAdapter {
	private final MemberDollRepository memberDollRepository;

	public void save(MemberDoll memberDoll) {
		memberDollRepository.save(new MemberDollEntity(memberDoll));
	}

	public boolean isExistByMember(long memberId) {
		return memberDollRepository.existsByMemberId(memberId);
	}

	public MemberDoll findByMember(long memberId) {
		return memberDollRepository.findByMemberId(memberId)
			.orElseThrow(() -> new SoftieException(
				ExceptionCode.NOT_FOUND,
				"Not Found MemberDoll, Member ID: " + memberId))
			.toDomain();
	}

	public void update(MemberDoll memberDoll) {
		val memberDollEntity = find(memberDoll.getId());
		memberDollEntity.update(memberDoll);
	}

	public void deleteByMember(long memberId) {
		memberDollRepository.deleteByMemberId(memberId);
	}

	private MemberDollEntity find(long id) {
		return memberDollRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "MemberDoll ID: " + id));
	}
}
