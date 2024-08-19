package com.soptie.server.domain.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.domain.usecase.MemberService;
import com.soptie.server.persistence.entity.DollType;
import com.soptie.server.persistence.adapter.MemberDeleter;
import com.soptie.server.persistence.adapter.MemberFinder;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.adapter.MemberDollSaver;
import com.soptie.server.persistence.entity.MemberDoll;
import com.soptie.server.persistence.adapter.MemberRoutineSaver;
import com.soptie.server.persistence.adapter.ConversationFinder;
import com.soptie.server.persistence.adapter.DollFinder;
import com.soptie.server.persistence.entity.Conversation;
import com.soptie.server.persistence.adapter.RoutineFinder;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

	private final ConversationFinder conversationFinder;
	private final MemberFinder memberFinder;
	private final MemberDeleter memberDeleter;
	private final DollFinder dollFinder;
	private final RoutineFinder routineFinder;
	private final MemberDollSaver memberDollSaver;
	private final MemberRoutineSaver memberRoutineSaver;

	@Override
	@Transactional
	public void createMemberProfile(MemberProfileCreateServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		member.checkMemberDollNonExist();
		createDailyRoutines(member, request.routines());
		createMemberDoll(member, request.dollType(), request.name());
	}

	@Override
	@Transactional
	public MemberCottonCountGetServiceResponse giveCotton(CottonGiveServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		val cottonCount = member.subtractAndGetCotton(request.cottonType());
		return MemberCottonCountGetServiceResponse.of(cottonCount);
	}

	@Override
	public MemberHomeInfoGetServiceResponse getMemberHomeInfo(MemberHomeInfoGetServiceRequest request) {
		val member = memberFinder.findById(request.memberId());
		member.checkMemberDollExist();
		val conversations = getConversations();
		return MemberHomeInfoGetServiceResponse.of(member, conversations);
	}

	@Override
	public void deleteMember(Member member) {
		memberDeleter.delete(member);
	}

	private void createDailyRoutines(Member member, List<Long> routineIds) {
		routineIds.forEach(id -> {
			val routine = routineFinder.findById(id);
			memberRoutineSaver.checkHasDeletedAndSave(member, routine);
		});
	}

	private void createMemberDoll(Member member, DollType dollType, String name) {
		val doll = dollFinder.findByType(dollType);
		val memberDoll = new MemberDoll(member, doll, name);
		memberDollSaver.save(memberDoll);
	}

	private List<String> getConversations() {
		return conversationFinder.findAll().stream().map(Conversation::getContent).toList();
	}
}
