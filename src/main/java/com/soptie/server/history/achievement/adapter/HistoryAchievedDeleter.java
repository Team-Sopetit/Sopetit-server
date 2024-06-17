package com.soptie.server.history.achievement.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.history.achievement.repository.HistoryAchievedRepository;
import com.soptie.server.member.entity.Member;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class HistoryAchievedDeleter {

	private final HistoryAchievedRepository achievedRepository;

	public void deleteByMember(Member member) {
		achievedRepository.deleteByMember(member);
	}
}
